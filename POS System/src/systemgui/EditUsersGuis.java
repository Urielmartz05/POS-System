package systemgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUIHelpers.TextPrompt;

public class EditUsersGuis {

    public static JTextField codeInput;
    public static JTextField nameInput;
    public static JComboBox<String> roleBox;
    public static JPasswordField passwordInput;

    public static void createNewUser(){

        // Create New User Main Panel
        JPanel newUserMainPanel = new JPanel();
        newUserMainPanel.setLayout(new BorderLayout());
        newUserMainPanel.setPreferredSize(new Dimension(400,300));
    
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        newUserMainPanel.add(titlePanel, BorderLayout.NORTH);

        // Title text
        JLabel titleTxt = new JLabel("Enter data below: ");
        titleTxt.setFont(titleTxt.getFont().deriveFont(18f));
        titlePanel.add(titleTxt);

        // Information Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(20,0,0,0));
        newUserMainPanel.add(infoPanel);

        // Input code of User
        codeInput = new JTextField();
        codeInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(codeInput);
        new TextPrompt("Code", codeInput);
        infoPanel.add(Box.createVerticalStrut(20));

        // Input name of User
        nameInput = new JTextField();
        nameInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(nameInput);
        new TextPrompt("Name", nameInput);
        infoPanel.add(Box.createVerticalStrut(20));

        // ComboBox with the role of workers
        String[] rolesList = {"Admin", "User"};
        roleBox = new JComboBox<>(rolesList);
        roleBox.setMaximumSize(new Dimension(320,40));
        infoPanel.add(roleBox);
        infoPanel.add(Box.createVerticalStrut(20));

        // Input Password
        passwordInput = new JPasswordField();
        passwordInput.setMaximumSize(new Dimension(320,40));
        infoPanel.add(passwordInput);
        new TextPrompt("Password", passwordInput);
        
        // JOption Pane with Input Fields
        int result = JOptionPane.showConfirmDialog(
            null,                       
            newUserMainPanel,
            "New User",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE    
        );

        
    } 
 
}
