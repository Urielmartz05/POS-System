package systemgui;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


public class OrdersHistoryPanel extends JPanel {
    public OrdersHistoryPanel() {initComponents();}
    public static JPanel OrdersHistoryPanel;
    private void initComponents() {
        setLayout(new BorderLayout());
        // Orders Panel Main Panel
        OrdersHistoryPanel = new JPanel();
        OrdersHistoryPanel.setLayout(new BorderLayout());

        // Nav Bar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1,2));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20,20,20,0));
        navBarPanel.setBackground(new Color(0xD52D5D));

        JPanel navLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navLeftPanel.setOpaque(false);

        JPanel navRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navRightPanel.setBorder(new EmptyBorder(0,0,0,20));
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
        SidePanel.setBorder(new EmptyBorder(20,0,20,0));
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
        JLabel SideLabel = new JLabel("     History     ");
        SideLabel.setFont(SideLabel.getFont().deriveFont(20f));
        SideLabel.setForeground(Color.BLACK);
        JLabel SecondSideLabel = new JLabel("     Orders     ");
        SecondSideLabel.setFont(SecondSideLabel.getFont().deriveFont(20f));
        SecondSideLabel.setForeground(Color.BLACK);
        SideOrdersHistory.setMaximumSize(new Dimension(Short.MAX_VALUE, 60));
        SideOrdersHistory.add(SideLabel, BorderLayout.CENTER);
        SideOrdersHistory.add(SecondSideLabel, BorderLayout.SOUTH);
        SideOrdersHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        SidePanel.add(SideOrdersHistory);

        //center top panel
        JPanel centerTopPanel = new JPanel();
        centerTopPanel.setLayout(new BorderLayout());
        centerTopPanel.setBackground(new Color(0xE8CEB0));
        JLabel centerTopLabel = new JLabel("Orders History");
        centerTopLabel.setFont(centerTopLabel.getFont().deriveFont(24f));
        centerTopLabel.setForeground(Color.WHITE);
        centerTopPanel.setBorder(new EmptyBorder(0,0,20,20));
        centerTopLabel.setBorder(new EmptyBorder(20,20,20,20));
        centerTopPanel.add(centerTopLabel, BorderLayout.NORTH);

        //center top buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setOpaque(false);
        JButton Resetbtn = new JButton("Reset History");
        Resetbtn.setFont(Resetbtn.getFont().deriveFont(14f));
        Resetbtn.setForeground(Color.WHITE);
        Resetbtn.setBackground(Color.RED);
        Resetbtn.setFocusPainted(false);
        JButton Exportbtn = new JButton("Export History");
        Exportbtn.setFont(Exportbtn.getFont().deriveFont(14f));
        Exportbtn.setForeground(Color.WHITE);
        Exportbtn.setBackground(Color.GRAY);
        Exportbtn.setFocusPainted(false);
        buttonsPanel.add(Resetbtn, BorderLayout.SOUTH);
        buttonsPanel.add(Exportbtn, BorderLayout.SOUTH);

        centerTopPanel.add(buttonsPanel, BorderLayout.WEST);


        //center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20,20,20,20));
        centerPanel.setBackground(new Color(0xE8CEB0));

        //table creation
        String[][] data = {};
        String[] ColumnNames={"Item","Type","Quantity","Code","Price"};
        DefaultTableModel model = new DefaultTableModel(data,ColumnNames);
        JTable ordersTable = new JTable(model);
        ordersTable.setFont(ordersTable.getFont().deriveFont(16f));
        ordersTable.getTableHeader().setFont(ordersTable.getTableHeader().getFont().deriveFont(18f));
        ordersTable.setRowHeight(30);
        ordersTable.setFillsViewportHeight(true);
        JScrollPane ordersTableScrollPane = new JScrollPane(ordersTable);
        ordersTableScrollPane.setPreferredSize(new Dimension(800, 500));
        centerPanel.setBorder(new EmptyBorder(50,50,50,50));
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(ordersTableScrollPane, BorderLayout.CENTER);

        OrdersHistoryPanel.add(navBarPanel,BorderLayout.NORTH);
        OrdersHistoryPanel.add(SidePanel,BorderLayout.WEST);
        OrdersHistoryPanel.add(centerPanel, BorderLayout.CENTER);


        add(OrdersHistoryPanel);

    }

}

