package systemgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.SalesLogic;

public class SalesWindows {

    public static JTextField productCodeField;
    public static JTextField productQuantityField;

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
    
}
