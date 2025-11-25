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
import java.util.ArrayList;
import java.util.HashMap;

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

import Controller.Authentication;
import Controller.OrdersHistory;
import Controller.SalesLogic;
import Model.Order;

public class OrdersHistoryPanel extends JPanel {

    public static JPanel OrdersHistoryPanel;
    public static DefaultTableModel model;
    public static JTable table;

    public OrdersHistoryPanel() {
        initComponents();
    }

    private void initComponents() {
        // Initialize Panel
        setLayout(new BorderLayout());
        
        // Main Panel
        OrdersHistoryPanel = new JPanel();
        OrdersHistoryPanel.setLayout(new BorderLayout());

        // Nav Bar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1,2));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20,20,20,0));
        navBarPanel.setBackground(new Color(0xD52D5D));
        OrdersHistoryPanel.add(navBarPanel,BorderLayout.NORTH);

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
        OrdersHistoryPanel.add(leftMainPanel, BorderLayout.WEST);

        // Text Panel in LeftPanel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        textPanel.setPreferredSize(new Dimension(150,70));
        textPanel.setBackground(new Color(0xede080));
        leftMainPanel.add(textPanel, BorderLayout.NORTH);

        // Title text
        JLabel miniTitle = new JLabel("History");
        miniTitle.setFont(miniTitle.getFont().deriveFont(24f));
        textPanel.add(miniTitle);

        // Center Main Panel
        JPanel centerMainPanel = new JPanel();
        centerMainPanel.setLayout(new BoxLayout(centerMainPanel, BoxLayout.Y_AXIS));
        centerMainPanel.setBackground(new Color(0xE8CEB0));
        OrdersHistoryPanel.add(centerMainPanel, BorderLayout.CENTER);

        // Title Panel
        JPanel mainTitlePanel = new JPanel();
        mainTitlePanel.setLayout(new GridLayout(1, 2));
        mainTitlePanel.setPreferredSize(new Dimension(1216, 70));
        mainTitlePanel.setOpaque(false);
        centerMainPanel.add(mainTitlePanel);

        // Main title
        JLabel mainTitle = new JLabel("Orders History");
        mainTitle.setFont(mainTitle.getFont().deriveFont(28f));
        mainTitle.setBorder(new EmptyBorder(0,20,0,0));
        mainTitlePanel.add(mainTitle);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        controlPanel.setPreferredSize(new Dimension(1216, 70));
        controlPanel.setBorder(new EmptyBorder(12,20,0,0));
        controlPanel.setOpaque(false);
        centerMainPanel.add(controlPanel);

        // Buttons
        JButton resetBtn = new JButton("Reset History");
        btnCustom(resetBtn);
        resetBtn.setBackground(new Color(0xFF3131));
        controlPanel.add(resetBtn);

        JButton exportBtn = new JButton("Export History");
        btnCustom(exportBtn);
        exportBtn.setBackground(new Color(0xD9D9D9));
        controlPanel.add(exportBtn);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setOpaque(false);
        centerMainPanel.add(tablePanel);

        // Table Creation
        ArrayList<Order> orders = OrdersHistory.ordersReader();
        Object[][] data = new Object[orders.size()][5];

        int i = 0;
        for (Order order : orders) {
            data[i][0] = i + 1; // Using index as code/ID for now since we don't have a unique ID in Order
            data[i][1] = order.getProduct();
            data[i][2] = order.getQuantity();
            data[i][3] = order.getPrice();
            data[i][4] = order.getTotal();
            i++;
        }

        String[] ColumnNames={"Code","Item","Quantity","Price","Total"};

        model = new DefaultTableModel(data ,ColumnNames);

        table = new JTable(model);
        table.setFont(table.getFont().deriveFont(16f));
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(18f));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1150, 450));
        tablePanel.add(scroll, new GridBagConstraints());

        add(OrdersHistoryPanel);
    }

    private void btnCustom(JButton button){
        button.setPreferredSize(new Dimension(200,40));
        button.setFont(button.getFont().deriveFont(18f));
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}

