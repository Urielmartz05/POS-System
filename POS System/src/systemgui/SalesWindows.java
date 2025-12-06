package systemgui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import systemgui.InventoryPanel;
import GUIHelpers.TextPrompt;
import Model.Product;
import Controller.SalesLogic;

public class SalesWindows {

    public static JTextField productCodeField;
    public static JTextField productQuantityField;
    public static HashMap<Integer,Double> productStock = new HashMap<>();
    private static JPanel addProduct() {
        
        // Base Panel
        JPanel addProductMainPanel = new JPanel();
        addProductMainPanel.setLayout(new BorderLayout());
        addProductMainPanel.setPreferredSize(new Dimension(300, 200));

        // Product Panel
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setPreferredSize(new Dimension(300, 200));
        addProductMainPanel.add(productPanel);

        // Product Code
        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeLabel.setFont(productCodeLabel.getFont().deriveFont(20f));
        productPanel.add(productCodeLabel);

        productPanel.add(Box.createVerticalStrut(10));
        
        productCodeField = new JTextField();
        productCodeField.setMaximumSize(new Dimension(500, 40));
        productCodeField.setAlignmentX(SwingConstants.CENTER);
        productCodeField.setFont(productCodeField.getFont().deriveFont(20f));
        productPanel.add(productCodeField);

        productPanel.add(Box.createVerticalStrut(10));

        // Product Quantity
        JLabel productQuantityLabel = new JLabel("Product Quantity:");
        productQuantityLabel.setFont(productQuantityLabel.getFont().deriveFont(20f));
        productPanel.add(productQuantityLabel);

        productPanel.add(Box.createVerticalStrut(10));

        productQuantityField = new JTextField();
        productQuantityField.setMaximumSize(new Dimension(500, 40));
        productQuantityField.setAlignmentX(SwingConstants.CENTER);
        productQuantityField.setFont(productQuantityField.getFont().deriveFont(20f));
        productPanel.add(productQuantityField);

        return addProductMainPanel;
    }

    public static void addProductGui() {

        JPanel addProductPanel = addProduct();

        int addProductWindow = JOptionPane.showConfirmDialog(
            null,
            addProductPanel,
            "Add product",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (addProductWindow == JOptionPane.YES_OPTION) {

            String productCode = productCodeField.getText();
            String productQuantity = productQuantityField.getText();
            
            // Validate Fields
            if (productCode.isEmpty() || productQuantity.isEmpty()) {

                JOptionPane.showMessageDialog(
                    null, 
                    "Please fill all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            else {
                SalesLogic.salesProcess();
            }

        }
        
    }
    public static void searchProductGui() {
        JPanel searchProductPanel = null;
        try {
            searchProductPanel = searchProduct();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int searchProductWindow = JOptionPane.showConfirmDialog(
                null,
                searchProductPanel,
                "search Product",
                JOptionPane.PLAIN_MESSAGE

        );
    }

    public static JPanel searchProduct() throws IOException {
        JPanel mainSearchPanel = new JPanel();
        mainSearchPanel.setSize(900,900);
        mainSearchPanel.setLayout(new BoxLayout(mainSearchPanel, BoxLayout.Y_AXIS));

        JPanel searchProduct = new JPanel();

        HashMap<Integer, Product> inventoryData;
        inventoryData = SalesLogic.inventoryReader();
        String[] columns = {"Item","Type","Quantity","Code","Price"};
        Object[][] data = new Object[inventoryData.size()][5];

        int i = 0;
        for (Integer elem : inventoryData.keySet()) {
            Product product = inventoryData.get(elem);
            data[i][0] = product.getItem();
            data[i][1] = product.getType();
            data[i][2] = product.getQuantity();
            data[i][3] = elem;
            data[i][4] = product.getPrice();
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(data,columns);
        JTextField productInputField = new JTextField();
        productInputField.setPreferredSize(new Dimension(300, 30));
        productInputField.setAlignmentX(SwingConstants.CENTER);
        productInputField.setFont(productInputField.getFont().deriveFont(20f));
        new TextPrompt("Search product", productInputField);
        searchProduct.add(productInputField);

        JPanel TablePanel = new JPanel();
        JTable searchTable = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productInputField.addActionListener(e -> {
            String text = productInputField.getText();
            if (text.trim().isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        searchTable.setRowSorter(sorter);
        searchTable.setFont(searchTable.getFont().deriveFont(12f));
        searchTable.getTableHeader().setFont(searchTable.getTableHeader().getFont().deriveFont(18f));
        searchTable.setRowHeight(25);
        searchTable.setFillsViewportHeight(true);
        TablePanel.add(searchTable);
        JScrollPane scroll = new JScrollPane(searchTable);
        scroll.setPreferredSize(new Dimension(600, 250));
        TablePanel.add(scroll, new GridBagConstraints());

        mainSearchPanel.add(searchProduct);
        mainSearchPanel.add(TablePanel);

        return mainSearchPanel;
    }

}
