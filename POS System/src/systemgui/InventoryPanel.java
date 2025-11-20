package systemgui;

import GUIHelpers.TextPrompt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

import Model.Inventory;
import Logic.InventoryFileEditor;

public class InventoryPanel extends JPanel {
    public InventoryPanel() {initComponents();}
    public static JPanel InventoryPanel;
    private void initComponents() {
        setLayout(new BorderLayout());
        // Orders Panel Main Panel
        InventoryPanel = new JPanel();
        InventoryPanel.setLayout(new BorderLayout());

        // Nav Bar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1, 2));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20, 20, 20, 0));
        navBarPanel.setBackground(new Color(0xD52D5D));

        JPanel navLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navLeftPanel.setOpaque(false);

        JPanel navRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navRightPanel.setBorder(new EmptyBorder(0, 0, 0, 20));
        navRightPanel.setOpaque(false);
        JButton exitbtn = new JButton("Exit To Menu");
        exitbtn.setBackground(new Color(0xD52D5D));
        exitbtn.setForeground(Color.WHITE);
        exitbtn.setFont(exitbtn.getFont().deriveFont(14f));
        exitbtn.setFocusPainted(false);
        exitbtn.setBorderPainted(false);
        JButton logOutbtn = new JButton("Log Out");
        logOutbtn.setBackground(new Color(0xD52D5D));
        logOutbtn.setForeground(Color.WHITE);
        logOutbtn.setFont(logOutbtn.getFont().deriveFont(14f));
        logOutbtn.setFocusPainted(false);
        logOutbtn.setBorderPainted(false);

        navRightPanel.add(exitbtn);
        navRightPanel.add(logOutbtn);
        navBarPanel.add(navLeftPanel);
        navBarPanel.add(navRightPanel);


        //Side Nav Bar Panel
        JPanel SidePanel = new JPanel();
        SidePanel.setLayout(new BoxLayout(SidePanel, BoxLayout.Y_AXIS));
        SidePanel.setMaximumSize(new Dimension(100, 1080));
        SidePanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        SidePanel.setBackground(new Color(0xD52D5D));


        //Side nav bar Icon
        ImageIcon restLogo = new ImageIcon(getClass().getResource("/Images/RestaurantLogo.png"));
        Image restLogoResize = restLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedRestLogo = new ImageIcon(restLogoResize);
        JLabel restIcon = new JLabel(resizedRestLogo);
        restIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        SidePanel.add(restIcon);

        JPanel SideOrdersHistory = new JPanel(new BorderLayout());
        SideOrdersHistory.setBackground(new Color(106, 111, 187));
        JLabel SideLabel = new JLabel("    Inventory    ");
        SideLabel.setFont(SideLabel.getFont().deriveFont(20f));
        SideLabel.setForeground(Color.BLACK);
        SideOrdersHistory.setMaximumSize(new Dimension(Short.MAX_VALUE, 60));
        SideOrdersHistory.add(SideLabel, BorderLayout.CENTER);
        SideOrdersHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        SidePanel.add(SideOrdersHistory);

        //center top panel
        JPanel centerTopPanel = new JPanel();
        centerTopPanel.setLayout(new BorderLayout());
        centerTopPanel.setBackground(new Color(0xE8CEB0));

        JPanel topStuffPanel = new JPanel();
        topStuffPanel.setLayout(new GridLayout(1, 2));
        topStuffPanel.setOpaque(false);
        JPanel stuffLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stuffLeftPanel.setOpaque(false);
        JLabel centerTopLabel = new JLabel("Products Inventory");
        centerTopLabel.setFont(centerTopLabel.getFont().deriveFont(24f));
        centerTopLabel.setForeground(Color.WHITE);
        centerTopPanel.setBorder(new EmptyBorder(0,0,20,20));
        centerTopLabel.setBorder(new EmptyBorder(20,20,20,20));
        stuffLeftPanel.add(centerTopLabel);

        JPanel topStuffRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topStuffRightPanel.setOpaque(false);
        JTextField searchBox = new JTextField(15);
        TextPrompt searchPlaceholder = new TextPrompt("Search product by name", searchBox);
        searchBox.setFont(searchBox.getFont().deriveFont(16f));
        topStuffRightPanel.add(searchBox);
        topStuffPanel.add(stuffLeftPanel);
        topStuffPanel.add(topStuffRightPanel);
        centerTopPanel.add(topStuffPanel, BorderLayout.NORTH);

        //center top buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setOpaque(false);

        JButton addBtn = new JButton("Add New Product");
        addBtn.setFont(addBtn.getFont().deriveFont(14f));
        addBtn.setForeground(Color.BLACK);
        addBtn.setBackground(new Color(12, 192, 223));
        addBtn.setFocusPainted(false);
        addBtn.setBorderPainted(false);

        JButton updateBtn = new JButton("Update Product");
        updateBtn.setFont(updateBtn.getFont().deriveFont(14f));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setBackground(Color.GRAY);
        updateBtn.setFocusPainted(false);
        updateBtn.setBorderPainted(false);

        JButton deleteBtn = new JButton("Delete Product");
        deleteBtn.setFont(deleteBtn.getFont().deriveFont(14f));
        deleteBtn.setForeground(Color.BLACK);
        deleteBtn.setBackground(Color.RED);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorderPainted(false);

        JButton importBtn = new JButton("Import Product");
        importBtn.setFont(importBtn.getFont().deriveFont(14f));
        importBtn.setForeground(Color.BLACK);
        importBtn.setBackground(Color.GRAY);
        importBtn.setFocusPainted(false);
        importBtn.setBorderPainted(false);

        JButton exportBtn = new JButton("Export Product");
        exportBtn.setFont(exportBtn.getFont().deriveFont(14f));
        exportBtn.setForeground(Color.BLACK);
        exportBtn.setBackground(Color.GRAY);
        exportBtn.setFocusPainted(false);
        exportBtn.setBorderPainted(false);

        buttonsPanel.add(addBtn, BorderLayout.SOUTH);
        buttonsPanel.add(updateBtn, BorderLayout.SOUTH);
        buttonsPanel.add(deleteBtn, BorderLayout.SOUTH);
        buttonsPanel.add(importBtn, BorderLayout.SOUTH);
        buttonsPanel.add(exportBtn, BorderLayout.SOUTH);

        centerTopPanel.add(buttonsPanel, BorderLayout.WEST);


        //center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20,20,20,20));
        centerPanel.setBackground(new Color(0xE8CEB0));

        //table creation
        InventoryFileEditor.dataInitializer();
        HashMap<Integer,Inventory> inventoryData = InventoryFileEditor.InventoryList;
        //data
        String[] ColumnNames={"Item","Type","Quantity","Code","Price"};
        Object[][] data = new Object[inventoryData.size()][5];
        int i = 0;
        for (Integer elem : inventoryData.keySet()) {
            Inventory inventory = inventoryData.get(elem);
            data[i][0] = inventory.getItem();
            data[i][1] = inventory.getType();
            data[i][2] = inventory.getQuantity();
            data[i][3] = elem;
            data[i][4] = inventory.getPrice();

            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data,ColumnNames);
        JTable productTable = new JTable(model);
        productTable.setFont(productTable.getFont().deriveFont(16f));
        productTable.getTableHeader().setFont(productTable.getTableHeader().getFont().deriveFont(18f));
        productTable.setRowHeight(30);
        productTable.setFillsViewportHeight(true);
        JScrollPane productsTableScrollPane = new JScrollPane(productTable);
        productsTableScrollPane.setPreferredSize(new Dimension(800, 500));
        centerPanel.setBorder(new EmptyBorder(50,50,50,50));
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(productsTableScrollPane, BorderLayout.CENTER);

        InventoryPanel.add(navBarPanel,BorderLayout.NORTH);
        InventoryPanel.add(SidePanel,BorderLayout.WEST);
        InventoryPanel.add(centerPanel, BorderLayout.CENTER);


        add(InventoryPanel);

    }

}