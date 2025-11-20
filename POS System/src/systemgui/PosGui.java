package systemgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PosGui extends JPanel {
    

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
        JLabel logOutTxt = new JLabel("Log out");
        logOutTxt.setFont(logOutTxt.getFont().deriveFont(24f));
        navRightPanel.add(logOutTxt);

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
        infoPanel.setPreferredSize(new Dimension (310,290));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        sellInfoPanel.add(infoPanel);

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

        // Delete Product btn
        JButton deleteProductBtn = new JButton("Delete");
        btnCustomControls(deleteProductBtn);
        deleteProductBtn.setBackground(Color.RED);
        centerTopPanel.add(deleteProductBtn);
        
        // Center bottom panel
        JPanel centerTablePanel = new JPanel();
        centerTablePanel.setLayout(new BorderLayout());
        centerTablePanel.setBackground(Color.GREEN);
        centerMainPanel.add(centerTablePanel);

        // Table Data
        String[] columns = { "ITEM", "CODE", "QUANTITY","PRICE", "TOTAL" };

        Object[][] data = {
            { 101, "Item 1",  "Admin",    "1234", "1234" },
            { 102, "Item 2",    "Cashier",  "abcd", "1234" },
            { 103, "Item 3", "Manager",  "pass", "1234" }
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
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

        JButton manualSearchBtn = new JButton("Search");
        btnCustom(manualSearchBtn);
        manualSearchBtn.setBackground(Color.WHITE);
        bottomLeftPanel.add(manualSearchBtn);

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

        JButton payBtn = new JButton("Pay");
        payBtn.setBackground(Color.GREEN);
        btnCustom(payBtn);
        payBtn.setForeground(Color.WHITE);
        bottomRightPanel.add(payBtn);

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

}
