package Controller;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Users;

public class Authentication {

    private static final File file = new File("POS System/src/Data/data.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Users userAuthentication(String accessCode, String password){

        Users correctUser = null;
        HashMap<Integer, Users> registerList = new HashMap<>();

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<HashMap<Integer, Users>>() {}.getType();
            registerList = gson.fromJson(reader, type);

            int access = Integer.parseInt(accessCode);
            for (Integer register : registerList.keySet()) {
                Users user = registerList.get(register);
                if (register == access && user.getPassword().equals(password)) {
                    correctUser = user;
                    break;
                }
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return correctUser;
    }


}
