package Controller;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Order;
import Model.Product;
import systemgui.PosGui;
import systemgui.SalesWindows;

public class SalesLogic {

    public static HashMap<Product, Integer> cart = new HashMap<>();
    public static ArrayList<Order> orders = new ArrayList<>();

    private static float subTotal = 0;
    private static float tax = 0;
    private static float total = 0;
    private static float pay = 0;

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

        // Add to Subtotal
        amountSetter();

    }

    public static void inventoryModifier(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Read Inventory
        HashMap<Integer, Product> inventory = inventoryReader();

        //Get table data
        DefaultTableModel model = PosGui.model;

        // Get data from inventory
        int codeColumn = 1;
        int quantityColumn = 2;

        for (int i = 0; i < model.getRowCount(); i++) {

            Object codes = model.getValueAt(i, codeColumn);
            Object quantities = model.getValueAt(i, quantityColumn);

            int code = Integer.parseInt(codes.toString());
            int quantity = Integer.parseInt(quantities.toString());


            if (inventory.containsKey(code)) {
                Product product = inventory.get(code);
                int newProductQuantity = product.getQuantity() - quantity;
                product.setQuantity(newProductQuantity);
                inventory.put(code, product);

                Order order = new Order(product.getItem(), quantity, product.getPrice(), quantity * product.getPrice());
                orders.add(order);
            }

            // Update data in inventory Json
            try (Writer writer = new FileWriter("POS System/src/Data/inventory.json")) {
                gson.toJson(inventory, writer);
            } catch (Exception e) {
                e.getStackTrace();
            }

        }
        
        OrdersHistory.ordersHistory();
        orders.clear();
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
            String productName = PosGui.table.getValueAt(row, 0).toString();
            int productQuantity = Integer.parseInt(PosGui.table.getValueAt(row, 2).toString());
            
            // Search item to delete
            for (Product item : cart.keySet()) {
                
                if (item.getItem().equals(productName) && cart.get(item) == productQuantity) {
                    cart.remove(item);
                    break;
                }
            }

            // Remove row from table
            model.removeRow(selectedRow);

            // Update Amount
            amountSetter();

        }
        
    }

    public static void deleteAllProducts(){
        cart.clear();
        DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();
        model.setRowCount(0);

        amountSetter();

    }

    public static void amountSetter(){

        for (Product element : cart.keySet()) {
            subTotal += element.getPrice() * cart.get(element);
            tax = (float) (subTotal * 0.16);
            total = subTotal + tax;
            pay = total;
        }

        PosGui.subTotalAmount.setText(String.valueOf("$ " + subTotal));
        PosGui.taxAmount.setText(String.valueOf("$ " + tax));
        PosGui.totalAmount.setText(String.valueOf("$ " + total));
        PosGui.payAmount.setText(String.valueOf("$ " + pay));
        
        subTotal = 0;
        tax = 0;
        total = 0;
        pay = 0;

    }

    public static HashMap<Integer, Product> inventoryReader(){
        
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
