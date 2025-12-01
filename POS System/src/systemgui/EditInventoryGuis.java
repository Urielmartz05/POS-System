package systemgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.EditInventory;
import GUIHelpers.TextPrompt;
import Model.Product;
public class EditInventoryGuis {
    public static JTextField itemCodeInput = new JTextField();
    public static JTextField itemNameInput = new JTextField();
    public static JTextField itemQuantityInput = new JTextField();
    public static JTextField itemPriceInput = new JTextField();
    public static JComboBox<String> itemTypeInput;

    public static void createNewItem() {
        
        JPanel editPanel = editGui(true);
        itemCodeInput.setText("");
        itemNameInput.setText("");
        itemQuantityInput.setText("");
        itemPriceInput.setText("");

        int result = JOptionPane.showConfirmDialog(
                null,
                editPanel,
                "New Item",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {

            String code = itemCodeInput.getText();
            String name = itemNameInput.getText();
            String type = (String) itemTypeInput.getSelectedItem();
            if (!itemQuantityInput.getText().isEmpty()) {int quantity = Integer.parseInt(itemQuantityInput.getText());}
            if (!itemPriceInput.getText().isEmpty()) { double price = Double.parseDouble(itemPriceInput.getText());}

            if (code.isEmpty() || name.isEmpty() ||  type.isEmpty()|| itemQuantityInput.getText().isEmpty() || itemPriceInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "All fields are required",
                        "Validation error!",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else {
                EditInventory.addNewItem();

            }

        }

    }
    public static void editItem() {

        JPanel panel = editGui(false);

        // Get specific user information
        try {
            HashMap<String, Product> inventoryList = EditInventory.readInventoryHashMap();


            int selectedRow = InventoryPanel.productTable.getSelectedRow();
            int selectedColumn = 0;
            int row = InventoryPanel.productTable.convertRowIndexToModel(selectedRow);
            Object value = InventoryPanel.productTable.getModel().getValueAt(row, 3);

            // Set data in input fields
            String itemCode = value.toString();
            Product inv = inventoryList.get(itemCode);

            // Fill fields with user info
            itemCodeInput.setText(itemCode);
            itemNameInput.setText(inv.getItem());

            // Show Edit User GUI
            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Update Item Info",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {

                String code = itemCodeInput.getText();
                String name = itemNameInput.getText();
                String type = (String) itemTypeInput.getSelectedItem();
                if (!itemQuantityInput.getText().isEmpty()) {int quantity = Integer.parseInt(itemQuantityInput.getText());}
                if (!itemPriceInput.getText().isEmpty()) { double price = Double.parseDouble(itemPriceInput.getText());}

                if (code.isEmpty() || name.isEmpty() || type.isEmpty()|| itemQuantityInput.getText().isEmpty() || itemPriceInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please fill blank fields!",
                            "Update User Info",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    EditInventory.editItem();
                }
            }
        }
        catch (IOException e) {
        }
    }

    private static JPanel editGui(boolean showCodeField){
        // Create New User Main Panel
        JPanel newUserMainPanel = new JPanel();
        newUserMainPanel.setLayout(new BorderLayout());
        newUserMainPanel.setPreferredSize(new Dimension(400,300));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        newUserMainPanel.add(titlePanel, BorderLayout.NORTH);

        // Title text
        JLabel titleTxt = new JLabel("Enter data below: ");
        titleTxt.setFont(titleTxt.getFont().deriveFont(18f));
        titlePanel.add(titleTxt);

        // Information Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(20,0,0,0));
        newUserMainPanel.add(infoPanel);

        // Input code of Item
        itemCodeInput = new JTextField();
        itemCodeInput.setMaximumSize(new Dimension(320,40));
        new TextPrompt("Code", itemCodeInput);

        if (showCodeField) {
            infoPanel.add(itemCodeInput);
            infoPanel.add(Box.createVerticalStrut(20));
        }

        // Input name of item
        itemNameInput = new JTextField();
        itemNameInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(itemNameInput);
        new TextPrompt("Name", itemNameInput);
        infoPanel.add(Box.createVerticalStrut(20));


        // Type of product
        String[] types = {"Unit", "Kg"};
        itemTypeInput = new JComboBox<>(types);
        itemTypeInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(itemTypeInput);

        infoPanel.add(Box.createVerticalStrut(20));

        // Input quantity of item
        itemQuantityInput = new JTextField();
        itemQuantityInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(itemQuantityInput);
        new TextPrompt("Quantity", itemQuantityInput);
        infoPanel.add(Box.createVerticalStrut(20));


        //Input price of item
        itemPriceInput = new JTextField();
        itemPriceInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(itemPriceInput);
        new TextPrompt("Price", itemPriceInput);
        infoPanel.add(Box.createVerticalStrut(20));

        return newUserMainPanel;
    }
}
