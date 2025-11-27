package systemgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Authentication;
import Controller.SalesLogic;
import Model.Product;
import main.View;

public class PosGui extends JPanel {
    
    public static DefaultTableModel model;
    public static JTable table;

    public static JLabel subTotalAmount;
    public static JLabel taxAmount;
    public static JLabel totalAmount;
    public static JLabel payAmount;

    public PosGui(){
        initComponents();
    }

    private void initComponents(){
        
        // Initialize Panel
        setLayout(new BorderLayout());

        // Table Main Panel
        JPanel tableMainPanel = new JPanel();
        tableMainPanel.setLayout(new BorderLayout());

        // Nav Bar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1,2));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20,20,20,0));
        navBarPanel.setBackground(new Color(0xD52D5D));
        tableMainPanel.add(navBarPanel,BorderLayout.NORTH);

        // Navbar Left Panel
        JPanel navLeftPanel = new JPanel();
        navLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        navLeftPanel.setOpaque(false);
        navBarPanel.add(navLeftPanel);

        // Restaurant Logo
        ImageIcon restLogo = new ImageIcon(getClass().getResource("/Images/RestaurantLogo.png"));
        Image restLogoResize = restLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedRestLogo = new ImageIcon(restLogoResize);
        JLabel restIcon = new JLabel(resizedRestLogo);
        navLeftPanel.add(restIcon);

        // Company Text
        JLabel companyText = new JLabel("MyCompany");
        companyText.setFont(companyText.getFont().deriveFont(24f));
        companyText.setForeground(Color.BLACK);
        navLeftPanel.add(companyText);

        // Navbar Right Panel
        JPanel navRightPanel = new JPanel();
        navRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        navRightPanel.setBorder(new EmptyBorder(0,0,0,20));
        navRightPanel.setOpaque(false);
        navBarPanel.add(navRightPanel);

        // Logout
        JLabel logOut = new JLabel("Log out");
        logOut.setFont(logOut.getFont().deriveFont(24f));
        logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        navRightPanel.add(logOut);

        Authentication.LogOut(logOut);

        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension (350,1266));
        rightPanel.setBackground(new Color(0xE8CEB0));
        tableMainPanel.add(rightPanel,BorderLayout.EAST);

        // Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.setOpaque(false);
        rightPanel.add(imagePanel);

        // Camera (Test Image)
        ImageIcon cameraImage = new ImageIcon(getClass().getResource("/Images/Camera.jpg"));
        Image cameraImageResize = cameraImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedCameraImage = new ImageIcon(cameraImageResize);
        JLabel cameraIcon = new JLabel(resizedCameraImage);
        imagePanel.add(cameraIcon);

        // Sell Information Panel
        JPanel sellInfoPanel = new JPanel();
        sellInfoPanel.setLayout(new GridBagLayout());
        sellInfoPanel.setBorder(new EmptyBorder(5,5,5,5));
        sellInfoPanel.setOpaque(false);
        rightPanel.add(sellInfoPanel);

        // Information Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension (310,190));
        infoPanel.setBorder(new EmptyBorder(15,15,15,15));
        infoPanel.setLayout(new GridLayout(1,2));
        sellInfoPanel.add(infoPanel);

        // Information Text Panel
        JPanel infoTextPanel = new JPanel();
        infoTextPanel.setLayout(new BoxLayout(infoTextPanel, BoxLayout.Y_AXIS));
        infoTextPanel.setBorder(new EmptyBorder(10,5,0,0));
        infoPanel.add(infoTextPanel);

        // Text Information
        JLabel subTotalText = new JLabel("Subtotal: ");
        customInfoText(subTotalText);
        infoTextPanel.add(subTotalText);

        infoTextPanel.add(Box.createVerticalStrut(5));

        JLabel taxText = new JLabel("Tax: ");
        customInfoText(taxText);
        infoTextPanel.add(taxText);

        infoTextPanel.add(Box.createVerticalStrut(5));

        JLabel totalText = new JLabel("Total: ");
        customInfoText(totalText);
        infoTextPanel.add(totalText);

        infoTextPanel.add(Box.createVerticalStrut(5));

        JLabel payText = new JLabel("Pay: ");
        customInfoText(payText);
        infoTextPanel.add(payText);

        // Amount Panel
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.Y_AXIS));
        amountPanel.setBorder(new EmptyBorder(10,0,10,0));
        infoPanel.add(amountPanel);

        // Amount Information
        subTotalAmount = new JLabel("$ 0.0");
        customAmountText(subTotalAmount);
        amountPanel.add(subTotalAmount);

        amountPanel.add(Box.createVerticalStrut(5));

        taxAmount = new JLabel("$ 0.0");
        customAmountText(taxAmount);
        amountPanel.add(taxAmount);

        amountPanel.add(Box.createVerticalStrut(5));

        totalAmount = new JLabel("$ 0.0");
        customAmountText(totalAmount);
        amountPanel.add(totalAmount);

        amountPanel.add(Box.createVerticalStrut(5));

        payAmount = new JLabel("$ 0.0");
        customAmountText(payAmount);
        amountPanel.add(payAmount);

        // Center Main Panel
        JPanel centerMainPanel = new JPanel();
        centerMainPanel.setLayout(new BoxLayout(centerMainPanel, BoxLayout.Y_AXIS));
        centerMainPanel.setBackground(new Color(0xE8CEB0));
        centerMainPanel.setBorder(new EmptyBorder(0,10,10,10));
        tableMainPanel.add(centerMainPanel);

        // Center top panel
        JPanel centerTopPanel = new JPanel();
        centerTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        centerTopPanel.setPreferredSize(new Dimension(500, 80));
        centerTopPanel.setBorder(new EmptyBorder(5,0,5,0));
        centerTopPanel.setBackground(new Color(0xE8CEB0));
        centerMainPanel.add(centerTopPanel, BorderLayout.NORTH);

        // Add Product btn
        JButton addProductBtn = new JButton("Add");
        btnCustomControls(addProductBtn);
        addProductBtn.setBackground(Color.GREEN);
        centerTopPanel.add(addProductBtn);

        addProductBtn.addActionListener(e -> {
            SalesWindows.addProductGui();
        });

        centerTopPanel.add(Box.createHorizontalStrut(5));

        // Delete Product btn
        JButton deleteProductBtn = new JButton("Delete");
        btnCustomControls(deleteProductBtn);
        deleteProductBtn.setBackground(Color.RED);
        centerTopPanel.add(deleteProductBtn);

        deleteProductBtn.addActionListener(e -> SalesLogic.deleteProduct());
        
        // Center bottom panel
        JPanel centerTablePanel = new JPanel();
        centerTablePanel.setLayout(new BorderLayout());
        centerTablePanel.setBackground(Color.GREEN);
        centerMainPanel.add(centerTablePanel);

        // Table Data
        String[] columns = { "ITEM", "CODE", "QUANTITY","PRICE", "TOTAL" };

        // Product to sell list
        HashMap<Product, Float> cart = SalesLogic.cart;

        // Product
        Object[][] product = new Object[cart.size()][5];

        int index = 0;
        for (Product elemt : cart.keySet()) {
            product[index][0] = elemt.getItem();
            product[index][1] = elemt.getType();
            product[index][2] = elemt.getQuantity();
            product[index][3] = elemt.getPrice();
            product[index][4] = elemt.getQuantity() * elemt.getPrice();
            index++;
        }

        model = new DefaultTableModel(product, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.getTableHeader().setFont(table.getFont().deriveFont(20f));
        table.setRowHeight(30);
        table.setFont(table.getFont().deriveFont(16f));
        
        JScrollPane scroll = new JScrollPane(table);
        centerTablePanel.add(scroll, BorderLayout.CENTER);


        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));
        bottomPanel.setPreferredSize(new Dimension (1366,100));
        bottomPanel.setBackground(new Color(0xCBC7B7));
        tableMainPanel.add(bottomPanel,BorderLayout.SOUTH);

        // Bottom Left Panel
        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomLeftPanel.setBorder(new EmptyBorder(5,5,5,5));
        bottomLeftPanel.setOpaque(false);
        bottomPanel.add(bottomLeftPanel);

        // Bottom left buttons
        JButton exitBtn = new JButton("Exit");
        exitBtn.setBackground(Color.WHITE);
        btnCustom(exitBtn);
        bottomLeftPanel.add(exitBtn);

        exitBtn.addActionListener(e -> {
            View.superMainLayout.show(View.superMainPanel, "ControlPanel");

            SalesLogic.cart.clear();
            DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();
            model.setRowCount(0);
        });

        JButton manualSearchBtn = new JButton("Search");
        btnCustom(manualSearchBtn);
        manualSearchBtn.setBackground(Color.WHITE);

        bottomLeftPanel.add(manualSearchBtn);
        manualSearchBtn.addActionListener(e -> {
            SalesWindows.searchProductGui();
        });

        // Bottom Right Panel
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.setBorder(new EmptyBorder(5,5,5,5));
        bottomRightPanel.setOpaque(false);
        bottomPanel.add(bottomRightPanel);

        // Bottom right buttons
        JButton cancelBtn = new JButton("Cancel");
        btnCustom(cancelBtn);
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        bottomRightPanel.add(cancelBtn);

        cancelBtn.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to cancel?",
                "Cancel",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (answer == JOptionPane.YES_OPTION) {
                SalesLogic.deleteAllProducts();
            }

        });

        bottomRightPanel.add(Box.createHorizontalStrut(5));

        JButton payBtn = new JButton("Pay");
        payBtn.setBackground(Color.GREEN);
        btnCustom(payBtn);
        payBtn.setForeground(Color.WHITE);
        bottomRightPanel.add(payBtn);

        payBtn.addActionListener(e -> {

            DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Nothing to pay!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            else{

                int answer = JOptionPane.showConfirmDialog(
                null,
                "Do you want to pay?",
                "Payment",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE

                );

                if (answer == JOptionPane.YES_OPTION) {
                    SalesLogic.inventoryModifier();
                    SalesLogic.deleteAllProducts();
                    JOptionPane.showMessageDialog(null, "Payment successfully completed!");
                }
            }
        
        });

        //keyboard actions
        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Add");
        this.getActionMap().put("Add", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    SalesWindows.addProductGui();

                }
        });
        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Delete");
        this.getActionMap().put("Delete", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Select a row to delete.",
                            "Error message",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                else {
                SalesLogic.deleteProduct();
                }
            }
        });
        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "Search");
        this.getActionMap().put("Search", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed f4");
                SalesWindows.searchProductGui();
            }
        });

        add(tableMainPanel);
    }

    private void btnCustom(JButton button){
        button.setFont(button.getFont().deriveFont(32f));
        button.setPreferredSize(new Dimension(210, 80));
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void btnCustomControls(JButton button){
        button.setFont(button.getFont().deriveFont(20f));
        button.setPreferredSize(new Dimension(140, 50));
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void customInfoText(JLabel label){
        label.setBorder(new EmptyBorder(0,10,0,0));
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(label.getFont().deriveFont(24f));
    }

    private void customAmountText(JLabel label){
        label.setBorder(new EmptyBorder(0,0,0,10));
        label.setAlignmentX(RIGHT_ALIGNMENT);
        label.setFont(label.getFont().deriveFont(24f));
    }


}
