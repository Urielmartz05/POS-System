package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Product;
import systemgui.EditInventoryGuis;
import systemgui.InventoryPanel;

public class EditInventory {
    
    public static int itemSelectedRow;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static HashMap<String,Product> inventoryList;
    private static final String FILE_PATH = "POS System/src/Data/inventory.json";
    
    public static void addNewItem(){

        try {
            inventoryList= readInventoryHashMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String code = EditInventoryGuis.itemCodeInput.getText();
        String name= EditInventoryGuis.itemNameInput.getText();
        Float quantity= Float.valueOf(EditInventoryGuis.itemQuantityInput.getText());
        Float price = Float.valueOf(EditInventoryGuis.itemPriceInput.getText());
        String type= (String) EditInventoryGuis.itemTypeInput.getSelectedItem();

        Product newItem= new Product(name,type,quantity,price);
        inventoryList.put(code,newItem);
        writeData();

        DefaultTableModel model = (DefaultTableModel) InventoryPanel.productTable.getModel();
        model.addRow(new Object[]{name, type, quantity, code, price});
    }

    public static void editItem() {

        // Read file before write to avoid delete data
        try {
            inventoryList = readInventoryHashMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get selected item data
        itemSelectedRow = InventoryPanel.productTable.getSelectedRow();
        if (itemSelectedRow == -1) return;

        int row = InventoryPanel.productTable.convertRowIndexToModel(itemSelectedRow);
        int codeColumnIndex = 3;

        String code = InventoryPanel.productTable.getValueAt(row, codeColumnIndex).toString();

        // Get data from EditInventoryGuis
        String itemName = EditInventoryGuis.itemNameInput.getText();
        String type= (String) EditInventoryGuis.itemTypeInput.getSelectedItem();
        if (itemName.isEmpty()) return;
        Float quantity = Float.valueOf(EditInventoryGuis.itemQuantityInput.getText());
        double price = Double.parseDouble(EditInventoryGuis.itemPriceInput.getText());

        // Update information
        if (itemName.isEmpty()) {
            return;
        }

        Product inv = inventoryList.get(code);
        if (inv != null) {
        inv.setItem(itemName);
        inv.setType(type);
        inv.setQuantity(quantity);
        inv.setPrice(price);
        inventoryList.put(code, inv);

        // Update json with new information
        writeData();

        DefaultTableModel model = (DefaultTableModel) InventoryPanel.productTable.getModel();
        model.setValueAt(itemName, row, 0);
        model.setValueAt(type, row, 1);
        model.setValueAt(quantity, row, 2);
        model.setValueAt(price, row, 4);

        }
    }

    public static void deleteItem(){

        int selectedRow = InventoryPanel.productTable.getSelectedRow();


        // Remove data from table
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) InventoryPanel.productTable.getModel();

            // Begin of delete data from Json
            int row = InventoryPanel.productTable.convertRowIndexToModel(selectedRow);
            int codeColumnIndex = 3;
            String code = InventoryPanel.productTable.getValueAt(row, codeColumnIndex).toString();

            // Read before delete data
            try {
                inventoryList = readInventoryHashMap();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Delete data from hashmap
            inventoryList.remove(code);

            // Delete Data from Json
            writeData();
            model.removeRow(selectedRow);

        }
    }

    public static void ExportInventory(){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int response = fileChooser.showSaveDialog(InventoryPanel.InventoryPanel);

        if (response == JFileChooser.APPROVE_OPTION) {
            String selectedDirectory = fileChooser.getSelectedFile().toString();
            File export  = new File(selectedDirectory, "InventoryExport.json");
            try(FileWriter writer = new FileWriter(export)) {
            gson.toJson(inventoryList, writer);
            JOptionPane.showMessageDialog(null,"Successfully exported Inventory File");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"unable to export Inventory File");
            }
        }

    }
    public static void ImportInventory(){
        int confirm = JOptionPane.showConfirmDialog(null,
                "Warning! this will overwrite your inventory file\nContinue?",
                "Confirm Import",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);
        int response= fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                HashMap<String, Product> importedData;
                try (InputStream inputStream = new FileInputStream(selectedFile);
                     InputStreamReader reader = new InputStreamReader(inputStream)) {

                    Type type = new TypeToken<HashMap<String, Product>>() {}.getType();
                    importedData = gson.fromJson(reader, type);
                    if (importedData != null && !importedData.isEmpty()) {
                        if (inventoryList == null) {inventoryList = readInventoryHashMap();}
                        inventoryList = importedData;
                        writeData();
                        DefaultTableModel model = (DefaultTableModel) InventoryPanel.productTable.getModel();
                        model.setRowCount(0);

                        for (String key : inventoryList.keySet()) {
                            Product p = inventoryList.get(key);
                            model.addRow(new Object[]{
                                    p.getItem(),
                                    p.getType(),
                                    p.getQuantity(),
                                    key,
                                    p.getPrice()
                            });
                        }

                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"unable to load JSON File");
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"unable to load JSON File");
            }
        }
        }
    }

    public static HashMap<String, Product> readInventoryHashMap() throws IOException {
        inventoryList = new HashMap<>();
        File file = new File(FILE_PATH);
        try (InputStream inputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            Type type = new TypeToken<HashMap<String, Product>>() {}.getType();
            inventoryList = gson.fromJson(reader, type);

            if(inventoryList == null) {
                inventoryList = new HashMap<>();
            }

        } catch (Exception e) {
            System.out.println("Error leyendo JSON: " + e.getMessage());
        }

        return inventoryList;
    }
    private static void writeData(){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(inventoryList, writer);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
