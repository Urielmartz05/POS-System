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
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUIHelpers.TextPrompt;
import Logic.UsersFileEditor;
import Model.Users;

public class UsersTable extends JPanel{

    public UsersTable(){
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

        // Back to Control Panel
        JLabel backMenu = new JLabel("Menu");
        backMenu.setFont(backMenu.getFont().deriveFont(24f));
        navRightPanel.add(backMenu);

        // Add space between menu and logout 
        navRightPanel.add(Box.createHorizontalStrut(10));

        // Logout
        JLabel logOutTxt = new JLabel("Log out");
        logOutTxt.setFont(logOutTxt.getFont().deriveFont(24f));
        navRightPanel.add(logOutTxt);

        // Left panel creation
        JPanel leftMainPanel = new JPanel();
        leftMainPanel.setLayout(new BorderLayout());
        leftMainPanel.setBackground(new Color(0xD52D5D));
        leftMainPanel.setPreferredSize(new Dimension(150, 668));
        tableMainPanel.add(leftMainPanel, BorderLayout.WEST);

        // Text Panel in LeftPanel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        textPanel.setPreferredSize(new Dimension(150,70));
        textPanel.setBackground(new Color(0xede080));
        leftMainPanel.add(textPanel, BorderLayout.NORTH);

        // Title text
        JLabel miniTitle = new JLabel("Admin");
        miniTitle.setFont(miniTitle.getFont().deriveFont(24f));
        textPanel.add(miniTitle);

        // Center Main Panel
        JPanel centerMainPanel = new JPanel();
        centerMainPanel.setLayout(new BoxLayout(centerMainPanel, BoxLayout.Y_AXIS));
        centerMainPanel.setBackground(new Color(0xE8CEB0));
        tableMainPanel.add(centerMainPanel, BorderLayout.CENTER);

        // Title Panel
        JPanel mainTitlePanel = new JPanel();
        mainTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 18));
        mainTitlePanel.setPreferredSize(new Dimension(1216, 70));
        mainTitlePanel.setOpaque(false);
        centerMainPanel.add(mainTitlePanel);

        // Main title
        JLabel mainTitle = new JLabel("Users Register");
        mainTitle.setFont(mainTitle.getFont().deriveFont(28f));
        mainTitlePanel.add(mainTitle);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,2));
        controlPanel.setPreferredSize(new Dimension(1216, 70));
        controlPanel.setOpaque(false);
        centerMainPanel.add(controlPanel);

        // Control Panel - Buttons Panel
        JPanel controlPanelBtnsPanel = new JPanel();
        controlPanelBtnsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 18));
        controlPanelBtnsPanel.setOpaque(false);
        controlPanel.add(controlPanelBtnsPanel);

        // Control Panel btns
        JButton newUserBtn = new JButton("New User");
        btnCustom(newUserBtn);
        newUserBtn.setBackground(new Color(0x0CCDF));
        controlPanelBtnsPanel.add(newUserBtn);

        newUserBtn.addActionListener(evt -> {
            EditUsersGuis.createNewUser();
        });

        JButton updateUserBtn = new JButton("Update User");
        btnCustom(updateUserBtn);
        updateUserBtn.setBackground(new Color(0xD9D9D9));
        controlPanelBtnsPanel.add(updateUserBtn);

        JButton deleteUserBtn = new JButton("Delete User");
        btnCustom(deleteUserBtn);
        deleteUserBtn.setBackground(new Color(0xFF3131));
        controlPanelBtnsPanel.add(deleteUserBtn);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 20));
        searchPanel.setOpaque(false);
        controlPanel.add(searchPanel);

        // Search Input
        JTextField searchInput = new JTextField();
        searchInput.setPreferredSize(new Dimension(300, 30));
        searchPanel.add(searchInput);

        TextPrompt searchPanelPhl = new TextPrompt("Search user by name", searchInput);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setOpaque(false);
        centerMainPanel.add(tablePanel);

        // Table Creation
        UsersFileEditor.dataInitializer();
        HashMap<Integer, Users> usersData = UsersFileEditor.userList;

        // Data store
        String[] columns = {"Code", "Name", "Role", "Password"};

        Object[][] data = new Object[usersData.size()][4];

        int i = 0;
        for (Integer elem : usersData.keySet()) {
            Users user = usersData.get(elem);
            data[i][0] = elem;
            data[i][1] = user.getName();
            data[i][2] = user.getRole();
            data[i][3] = user.getPassword();
            i++;
        }

        JTable table = new JTable(data, columns);
        table.setFont(table.getFont().deriveFont(16f));
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(18f));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1150, 450));
        tablePanel.add(scroll, new GridBagConstraints());

        add(tableMainPanel);
    }

    private void btnCustom(JButton button){
        button.setFont(button.getFont().deriveFont(18f));
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
}