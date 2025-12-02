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
import java.io.IOException;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Controller.Authentication;
import Controller.EditInventory;
import GUIHelpers.TextPrompt;
import Logic.InventoryFileEditor;
import Model.Product;

public class InventoryPanel extends JPanel {

    public static JPanel InventoryPanel;
    public static DefaultTableModel invModel;
    public static JTable productTable;
    private static JTextField searchInput;
    public InventoryPanel() {
        initComponents();
    }

    private void initComponents() {
        // Initialize Panel
        setLayout(new BorderLayout());
        
        // Main Panel
        InventoryPanel = new JPanel();
        InventoryPanel.setLayout(new BorderLayout());

        // Nav Bar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1,2));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20,20,20,0));
        navBarPanel.setBackground(new Color(0xD52D5D));
        InventoryPanel.add(navBarPanel,BorderLayout.NORTH);

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

        // Back to Control Panel
        JLabel backMenu = new JLabel("Menu");
        backMenu.setFont(backMenu.getFont().deriveFont(24f));
        backMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        navRightPanel.add(backMenu);

        ControlPanel.controlPanelBack(backMenu);

        // Add space between menu and logout 
        navRightPanel.add(Box.createHorizontalStrut(10));

        // Logout
        JLabel logOut = new JLabel("Log out");
        logOut.setFont(logOut.getFont().deriveFont(24f));
        logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        navRightPanel.add(logOut);

        Authentication.LogOut(logOut);

        // Left panel creation
        JPanel leftMainPanel = new JPanel();
        leftMainPanel.setLayout(new BorderLayout());
        leftMainPanel.setBackground(new Color(0xD52D5D));
        leftMainPanel.setPreferredSize(new Dimension(150, 668));
        InventoryPanel.add(leftMainPanel, BorderLayout.WEST);

        // Text Panel in LeftPanel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        textPanel.setPreferredSize(new Dimension(150,70));
        textPanel.setBackground(new Color(0xede080));
        leftMainPanel.add(textPanel, BorderLayout.NORTH);

        // Title text
        JLabel miniTitle = new JLabel("Inventory");
        miniTitle.setFont(miniTitle.getFont().deriveFont(24f));
        textPanel.add(miniTitle);

        // Center Main Panel
        JPanel centerMainPanel = new JPanel();
        centerMainPanel.setLayout(new BoxLayout(centerMainPanel, BoxLayout.Y_AXIS));
        centerMainPanel.setBackground(new Color(0xE8CEB0));
        InventoryPanel.add(centerMainPanel, BorderLayout.CENTER);

        // Title Panel
        JPanel mainTitlePanel = new JPanel();
        mainTitlePanel.setLayout(new GridLayout(1, 2));
        mainTitlePanel.setPreferredSize(new Dimension(1216, 70));
        mainTitlePanel.setOpaque(false);
        centerMainPanel.add(mainTitlePanel);

        // Main title
        JLabel mainTitle = new JLabel("Products Inventory");
        mainTitle.setFont(mainTitle.getFont().deriveFont(28f));
        mainTitle.setBorder(new EmptyBorder(0,20,0,0));
        mainTitlePanel.add(mainTitle);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 20));
        searchPanel.setOpaque(false);
        mainTitlePanel.add(searchPanel);

        // Search Input
        searchInput = new JTextField();
        searchInput.setPreferredSize(new Dimension(300, 30));
        searchInput.addActionListener(e -> {
            searchBar();
        });
        searchPanel.add(searchInput);
        new TextPrompt("Search product", searchInput);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        controlPanel.setPreferredSize(new Dimension(1216, 70));
        controlPanel.setBorder(new EmptyBorder(12,20,0,0));
        controlPanel.setOpaque(false);
        centerMainPanel.add(controlPanel);

        // Buttons
        JButton addBtn = new JButton("Add Product");
        btnCustom(addBtn);
        addBtn.setBackground(new Color(0x0CCDF));
        addBtn.addActionListener(evt ->{
            EditInventoryGuis.createNewItem();
        });
        controlPanel.add(addBtn);

        JButton updateBtn = new JButton("Update");
        btnCustom(updateBtn);
        updateBtn.setBackground(new Color(0xD9D9D9));
        updateBtn.addActionListener(evt -> {

            if (productTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select a Product!",
                        "Update Product Info",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else{
                EditInventoryGuis.editItem();
            }


        });
        controlPanel.add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        btnCustom(deleteBtn);
        deleteBtn.setBackground(new Color(0xFF3131));
        deleteBtn.addActionListener(evt -> {
            if (productTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select a Product!",
                        "Delete Product Info",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else{
                EditInventory.deleteItem();
            }
        });
        controlPanel.add(deleteBtn);

        JButton importBtn = new JButton("Import");
        btnCustom(importBtn);
        importBtn.setBackground(new Color(0xD9D9D9));
        importBtn.addActionListener(e -> {
            EditInventory.ImportInventory();
        });
        controlPanel.add(importBtn);

        JButton exportBtn = new JButton("Export");
        btnCustom(exportBtn);
        exportBtn.setBackground(new Color(0xD9D9D9));
        exportBtn.addActionListener(e -> {
            EditInventory.ExportInventory();
        });
        controlPanel.add(exportBtn);

        

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setOpaque(false);
        centerMainPanel.add(tablePanel);

        // Table Creation
        InventoryFileEditor.dataInitializer();

        HashMap<String, Product> inventoryData;
        try {
            inventoryData = EditInventory.readInventoryHashMap();
        } catch (IOException e) {
            inventoryData = new HashMap<>();
            e.printStackTrace();
        }

        // Data store
        String[] columns = {"Item","Type","Quantity","Code","Price"};
        Object[][] data = new Object[inventoryData.size()][5];

        int i = 0;
        for (String elem : inventoryData.keySet()) {
            Product product = inventoryData.get(elem);
            data[i][0] = product.getItem();
            data[i][1] = product.getType();
            data[i][2] = product.getQuantity();
            data[i][3] = elem;
            data[i][4] = product.getPrice();
            i++;
        }

        invModel = new DefaultTableModel(data,columns);
        productTable = new JTable(invModel);
        productTable.setFont(productTable.getFont().deriveFont(16f));
        productTable.getTableHeader().setFont(productTable.getTableHeader().getFont().deriveFont(18f));
        productTable.setRowHeight(30);
        productTable.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(productTable);
        scroll.setPreferredSize(new Dimension(1150, 450));
        tablePanel.add(scroll, new GridBagConstraints());

        add(InventoryPanel);
    }
    private void searchBar(){
        DefaultTableModel obj = (DefaultTableModel)productTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(obj);
        productTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchInput.getText()));
    }

    private void btnCustom(JButton button){
        button.setPreferredSize(new Dimension(150,40));
        button.setFont(button.getFont().deriveFont(18f));
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}