package Controller;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Users;
import systemgui.EditUsersGuis;
import systemgui.UsersTable;

public class EditUsers {

    public static int selectedRow;

    public static void addNewUser(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap<Integer, Users> usersList = new HashMap<>();

        // Read data before write to avoid delete data
        try (InputStream inputStream = EditUsers.class.getResourceAsStream("/Data/data.json")) {
            Type type = new TypeToken<HashMap<Integer, Users>>() {}.getType();
            InputStreamReader reader = new InputStreamReader(inputStream);
            usersList = gson.fromJson(reader, type);
        } catch (Exception e) {
              e.printStackTrace();
        }

        // Get new User information
        int code = Integer.parseInt(EditUsersGuis.codeInput.getText());
        String name = EditUsersGuis.nameInput.getText();
        String role = (String) EditUsersGuis.roleBox.getSelectedItem();
        String password = new String(EditUsersGuis.passwordInput.getPassword());

        Users newUser = new Users(password, name, role);
        usersList.put(code, newUser);

        try (Writer writer = new FileWriter("POS System/src/Data/data.json")) {
            gson.toJson(usersList, writer);
            writer.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        
    }

    public static void deleteUser(){

        int selectedRow = UsersTable.table.getSelectedRow();
        int selectedColumn = 0;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap<Integer, Users> usersList = new HashMap<>();

        // Remove data from table
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) UsersTable.table.getModel();
           
            // Begin of delete data from Json
            int modelRow = UsersTable.table.convertRowIndexToModel(selectedRow);
            int modelCol = UsersTable.table.convertColumnIndexToModel(selectedColumn);

            int code = (int) model.getValueAt(modelRow, modelCol);

            // Read before delete data
            try (InputStream inputStream = EditUsers.class.getResourceAsStream("/Data/data.json")) {
            
                Type type = new TypeToken<HashMap<Integer, Users>>() {}.getType();
                InputStreamReader reader = new InputStreamReader(inputStream);
                usersList = gson.fromJson(reader, type);
  
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Delete data from hashmap
            usersList.remove(code);

            // Delete Data from Json
            try (Writer writer = new FileWriter("POS System/src/Data/data.json")) {
                gson.toJson(usersList, writer);
            } catch (Exception e) {
                e.getStackTrace();
            }

            model.removeRow(selectedRow);

        }
    }

    public static void editUsersInfo(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap<Integer, Users> usersList = new HashMap<>();

        // Read file before write to avoid delete data
        try (InputStream inputStream = EditUsers.class.getResourceAsStream("/Data/data.json")) {
            Type type = new TypeToken<HashMap<Integer, Users>>() {}.getType();
            InputStreamReader reader = new InputStreamReader(inputStream);
            usersList = gson.fromJson(reader, type);

        } catch (Exception e) {
            e.getStackTrace();
        }

        // Get selected user data
        selectedRow = UsersTable.table.getSelectedRow();
        int selectedColumn = 0;

        int row = UsersTable.table.convertRowIndexToModel(selectedRow);
        int column = UsersTable.table.convertColumnIndexToModel(selectedColumn);

        // Set data in input fields
        int code = Integer.parseInt(UsersTable.table.getValueAt(row, column).toString());

        // Get data from EditUserGui
        String userName = EditUsersGuis.nameInput.getText();
        String userRole = (String) EditUsersGuis.roleBox.getSelectedItem();
        String userPassword = new String(EditUsersGuis.passwordInput.getPassword());

        // Update information
        if (userName.isEmpty()) {
            return;
        }

        Users user = usersList.get(code);
        user.setName(userName);
        user.setRole(userRole);

        if (!userPassword.isEmpty()) {
            user.setPassword(userPassword);
        }
        usersList.put(code, user);

        // Update json with new information
        try (Writer writer = new FileWriter("POS System/src/Data/data.json")) {
            gson.toJson(usersList, writer);
        } catch (Exception e) {
            e.getStackTrace();
        }

        DefaultTableModel model = (DefaultTableModel) UsersTable.table.getModel();
        model.setValueAt(user.getName(), row, 1);
        model.setValueAt(user.getRole(), row, 2);
        model.setValueAt(user.getPassword(), row, 3);

    }

    public static HashMap<Integer, Users> readUserHashMap(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap<Integer, Users> usersList = new HashMap<>();

        try (InputStream inputStream = EditUsers.class.getResourceAsStream("/Data/data.json")) {
            Type type = new TypeToken<HashMap<Integer, Users>>() {}.getType();
            InputStreamReader reader = new InputStreamReader(inputStream);
            usersList = gson.fromJson(reader, type);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return usersList;
    }

}
