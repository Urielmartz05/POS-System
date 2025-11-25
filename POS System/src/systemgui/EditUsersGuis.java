package systemgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.EditUsers;
import GUIHelpers.TextPrompt;
import Model.Users;

public class EditUsersGuis {

    public static JTextField codeInput = new JTextField();
    public static JTextField nameInput = new JTextField();
    public static JComboBox<String> roleBox;
    public static JPasswordField passwordInput;

    public static void createNewUser(){

        JPanel panel = editGui(true);
        codeInput.setText("");
        nameInput.setText("");

        // JOption Pane with Input Fields
        int result = JOptionPane.showConfirmDialog(
            null,                       
            panel,
            "New User",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE    
        );

        if (result == JOptionPane.OK_OPTION) {

            String code = codeInput.getText();
            String name = nameInput.getText();
            String role = (String) roleBox.getSelectedItem();
            String password = new String(passwordInput.getPassword());
            
            if (code.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "All fields are required",
                    "Validation error!",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            else {
                HashMap<Integer, Users> usersList = EditUsers.readUserHashMap();

                if (usersList.containsKey(Integer.valueOf(code))) {
                    JOptionPane.showMessageDialog(null, "Code not available");
                    return;
                }

                EditUsers.addNewUser();
                UsersTable.model.addRow(new Object[]{ code, name, role, password });
            }
        }
    } 

    // EditUsers.editUsersInfo();
    public static void editUserInfo(){

        JPanel panel = editGui(false);

        // Get specific user information
        HashMap<Integer, Users> usersList = EditUsers.readUserHashMap();
        int selectedRow = UsersTable.table.getSelectedRow();
        int selectedColumn = 0;
        int row = UsersTable.table.convertRowIndexToModel(selectedRow);
        int column = UsersTable.table.convertColumnIndexToModel(selectedColumn);

        // Set data in input fields
        int userCode = Integer.parseInt(UsersTable.table.getValueAt(row, column).toString());
        Users user = usersList.get(userCode);
        
        // Fill fields with user info
        codeInput.setText(String.valueOf(userCode));
        nameInput.setText(user.getName());

        // Show Edit User GUI
        int result = JOptionPane.showConfirmDialog(
            null,                       
            panel,
            "Update User Info",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE    
        );

        if (result == JOptionPane.OK_OPTION) {

            String code = codeInput.getText();
            String name = nameInput.getText();
            String role = (String) roleBox.getSelectedItem();
            String password = new String(passwordInput.getPassword());

            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Please fill blank fields!",
                    "Update User Info",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            else{
                EditUsers.editUsersInfo();
            }
        }
    }


    private static JPanel editGui(boolean showCodeField){
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
        new TextPrompt("Code", codeInput);

        if (showCodeField) {
            infoPanel.add(codeInput);
            infoPanel.add(Box.createVerticalStrut(20));
        }

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

        return newUserMainPanel;
    }
 
}
