package Controller;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Users;
import systemgui.EditUsersGuis;

public class AddUsers {

    public static void addNewUser(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap<Integer, Users> usersList = new HashMap<>();

        // Read data before write for avoid delete data
        try (InputStream inputStream = AddUsers.class.getResourceAsStream("/Data/data.json")) {
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

        // Write new data in json archive
        try {
            URL resource = AddUsers.class.getResource("/Data/data.json");
            String filePath = Paths.get(resource.toURI()).toString();
            
            Writer writer = new FileWriter(filePath);
            gson.toJson(usersList, writer);
            writer.close();
            
        } catch (Exception e) {
              e.printStackTrace();
        }
        

    }

}
