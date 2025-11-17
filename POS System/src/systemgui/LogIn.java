package systemgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUIHelpers.TextPrompt;
public class LogIn extends JPanel{

    public static JTextField emailInput;
    public static JButton loginBtn;
    public static JPasswordField passwordInput;
    public static JLabel alertLabel;

    public static String accessCode;
    private static char[] pass;
    public static String password;

    public static boolean isLogInInValid;


    public LogIn(){
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
        navBarPanel.setMaximumSize(new Dimension(1366, 100));
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
        emailInput = new JTextField();
        emailInput.setMaximumSize(new Dimension(300,30));
        emailInput.setFont(emailInput.getFont().deriveFont(16f));
        TextPrompt emailPlaceholder = new TextPrompt("Email",emailInput);
        logPanel.add(emailInput);

        logPanel.add(Box.createVerticalStrut(8));

        // Show Alert
        alertLabel = new JLabel("Email or password isn't valid!");
        alertLabel.setForeground(Color.RED);
        alertLabel.setAlignmentX(CENTER_ALIGNMENT);
        alertLabel.setVisible(true);
        logPanel.add(alertLabel);

        logPanel.add(Box.createVerticalStrut(8));

        // Password Input TextField
        passwordInput = new JPasswordField();
        passwordInput.setMaximumSize(new Dimension(300,30));
        TextPrompt passwordPlaceholder= new TextPrompt("Password", passwordInput);
        logPanel.add(passwordInput);
        logPanel.add(Box.createVerticalStrut(30));

        // Log In Button
        loginBtn = new JButton("Log In");
        loginBtn.setFont(loginBtn.getFont().deriveFont(18f));
        loginBtn.setBackground(new Color(0xD52D5D));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        logPanel.add(loginBtn);

        add(SalesManLogInMainPanel);
    }

    // Login Btn Setter and Getter
    public static JButton getLoginBtn() {
        return loginBtn;
    }

    public static void setLoginBtn(JButton loginBtn) {
        LogIn.loginBtn = loginBtn;
    }
}
