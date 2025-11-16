package Controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Users;

public class Authentication {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Users userAuthentication(String accessCode, String password){

        Users correctUser = null;
        HashMap<Integer, Users> registerList = new HashMap<>();

        try (InputStream inputStream = Authentication.class.getResourceAsStream("/Data/data.json");
             InputStreamReader reader = new InputStreamReader(inputStream)) {
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
