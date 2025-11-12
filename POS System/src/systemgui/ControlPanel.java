package systemgui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import logic.ControlPanelCreation;

public class ControlPanel extends JPanel {

    public static JPanel controlPanelMainPanel;

    public ControlPanel() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // Control Panel Main Panel
        controlPanelMainPanel = new JPanel();
        controlPanelMainPanel.setLayout(new BorderLayout());

        // Nav Bar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1,2));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20,20,20,0));
        navBarPanel.setBackground(new Color(0xD52D5D));
        controlPanelMainPanel.add(navBarPanel,BorderLayout.NORTH);

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

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(0xE8CEB0));
        controlPanelMainPanel.add(centerPanel, BorderLayout.CENTER);

        // Buttons Container
        JPanel btnsContainer = new JPanel();
        btnsContainer.setLayout(new GridLayout(1,3,10,0));
        btnsContainer.setPreferredSize(new Dimension(1150,300));
        btnsContainer.setOpaque(false);
        centerPanel.add(btnsContainer, new GridBagConstraints());

        // Generate Admin Control Panel
        ControlPanelCreation controlPanel = new ControlPanelCreation();
        JButton[] adminBtns = controlPanel.generateAdminPanel();

        for (int i = 0; i < adminBtns.length; i++) {

            btnsContainer.add(adminBtns[i]);
        }

        // Generate Users Control Panel
        // ControlPanelCreation controlPanel = new ControlPanelCreation();
        // JButton[] usersBtns = controlPanel.generateUserPanel();
        
        // for (int i = 0; i < usersBtns.length; i++) {
        //     btnsContainer.add(usersBtns[i]);
        // }

        

        add(controlPanelMainPanel);
    }
    
}