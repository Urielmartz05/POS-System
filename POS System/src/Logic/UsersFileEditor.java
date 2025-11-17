package Logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Users;

public class UsersFileEditor {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static HashMap<Integer, Users> userList = new HashMap<>();

    public static void dataInitializer(){
        try {
            UsersFileEditor.usersReader(); 
        } catch (IOException e) {
            System.err.println("Data cannot be loaded");
            e.printStackTrace();
            return; 
        }
    }

    // Read File
    public static HashMap<Integer, Users> usersReader() throws IOException{

        try (InputStream inputStream = UsersFileEditor.class.getResourceAsStream("/Data/data.json")) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<HashMap<Integer, Users>>() {}.getType();
            userList = gson.fromJson(reader, type);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return userList;
    }
    
}
