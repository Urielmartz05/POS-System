package Controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Product;
import systemgui.PosGui;
import systemgui.SalesWindows;

public class SalesLogic {

    public static HashMap<Product, Integer> cart = new HashMap<>();

    public static void salesProcess(){
        
        // Read Inventory
        HashMap<Integer, Product> inventory = inventoryReader();

        // Get data from sales window
        String productCode = SalesWindows.productCodeField.getText();
        int productQuantity = Integer.parseInt(SalesWindows.productQuantityField.getText());

        // Validate if product exists
        if (!inventory.containsKey(Integer.parseInt(productCode)) || inventory.get(Integer.parseInt(productCode)).getQuantity() < productQuantity) {
            JOptionPane.showMessageDialog(
                null, 
                "Product not available",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Add product to cart
        Product product = inventory.get(Integer.parseInt(productCode));
        cart.put(product, productQuantity);

        // Update table
        DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();
        model.addRow(new Object[] {product.getItem(), productCode, productQuantity, product.getPrice(), productQuantity * product.getPrice()});

    }

    public static void deleteProduct(){

        int selectedRow = PosGui.table.getSelectedRow();
        int selectedColumn = 1;


        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();

            // Get exact row and column
            int row = PosGui.table.convertRowIndexToModel(selectedRow);
            int column = PosGui.table.convertColumnIndexToModel(selectedColumn);

            // Get product code
            int productCode = Integer.parseInt(PosGui.table.getValueAt(row, column).toString());

            // Remove product from cart
            cart.remove(productCode);

            // Remove row from table
            model.removeRow(selectedRow);
            
        }
        
    }

    public static void deleteAllProducts(){
        cart.clear();
        DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();
        model.setRowCount(0);
    }

    private static HashMap<Integer, Product> inventoryReader(){
        
        HashMap<Integer, Product> inventory = new HashMap<>();

        try (InputStream inputStream = SalesLogic.class.getResourceAsStream("/Data/inventory.json")) {
            Type type = new TypeToken<HashMap<Integer, Product>>() {}.getType();
            InputStreamReader reader = new InputStreamReader(inputStream);
            inventory = new Gson().fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventory;
    }

}
