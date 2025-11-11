package systemgui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SalesManLogIn extends JPanel{

    public SalesManLogIn(){
        initComponents();
    }

    private void initComponents(){

        setLayout(new BorderLayout());

        // Main Panel
        JPanel SalesManLogInMainPanel = new JPanel();
        SalesManLogInMainPanel.setLayout(new BorderLayout());

        // NavBar Panel
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        navBarPanel.setMaximumSize(new Dimension(1080, 100));
        navBarPanel.setBorder(new EmptyBorder(20,20,20,0));
        navBarPanel.setBackground(new Color(0xD52D5D));
        SalesManLogInMainPanel.add(navBarPanel,BorderLayout.NORTH);

        // Restaurant Logo
        ImageIcon restLogo = new ImageIcon(getClass().getResource("/Images/RestaurantLogo.png"));
        Image restLogoResize = restLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedRestLogo = new ImageIcon(restLogoResize);
        JLabel restIcon = new JLabel(resizedRestLogo);
        navBarPanel.add(restIcon);
        

        // Admin Text
        JLabel companyText = new JLabel("MyCompany");
        companyText.setFont(companyText.getFont().deriveFont(24f));
        companyText.setForeground(Color.BLACK);
        navBarPanel.add(companyText);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(0xE8CEB0));
        SalesManLogInMainPanel.add(centerPanel, BorderLayout.CENTER);

        // Log Panel
        JPanel logPanel;
        logPanel = new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));
        logPanel.setPreferredSize(new Dimension(450,450));
        logPanel.setBorder(new EmptyBorder(20,20,20,20));
        logPanel.setBackground(new Color(0xFFFFFF));
        centerPanel.add(logPanel, new GridBagConstraints());

        // User Image
        ImageIcon img = new ImageIcon(getClass().getResource("/Images/UserIcon.png"));
        Image userImgResize = img.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon userImg = new ImageIcon(userImgResize);
        JLabel userIcon = new JLabel(userImg);
        userIcon.setAlignmentX(CENTER_ALIGNMENT);
        logPanel.add(userIcon);

        // User Text
        JLabel userTxt = new JLabel("User");
        userTxt.setAlignmentX(CENTER_ALIGNMENT);
        userTxt.setFont(userTxt.getFont().deriveFont(22f));
        logPanel.add(userTxt);

        logPanel.add(Box.createVerticalStrut(40));

        // Email Input TextField
        JTextField emailInput = new JTextField();
        emailInput.setMaximumSize(new Dimension(300,30));
        logPanel.add(emailInput);

        logPanel.add(Box.createVerticalStrut(20));

        // Password Input TextField
        JTextField passwordInput = new JTextField();
        passwordInput.setMaximumSize(new Dimension(300,30));
        logPanel.add(passwordInput);

        logPanel.add(Box.createVerticalStrut(30));

        // Log In Button
        JButton loginBtn = new JButton("Log In");
        loginBtn.setFont(loginBtn.getFont().deriveFont(18f));
        loginBtn.setBackground(new Color(0xD52D5D));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        logPanel.add(loginBtn);

        add(SalesManLogInMainPanel);
    }
	
}
