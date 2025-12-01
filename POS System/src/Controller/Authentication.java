package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Users;
import main.View;
import systemgui.CloseOperations;
import systemgui.ControlPanel;
import systemgui.LogIn;


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

    public static void LogOut(JLabel logOut){
        logOut.addMouseListener(new MouseAdapter() {
    
            @Override
            public void mouseClicked(MouseEvent e){
                
                int answer = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to log out?",
                    "Log out",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (answer == JOptionPane.YES_OPTION) {

                    SalesLogic.deleteAllProducts();

                    if (CloseOperations.productsAmount != null) {
                        CloseOperations.productsAmount.setText("0");
                    }
                    if (CloseOperations.salesAmount != null) {
                        CloseOperations.salesAmount.setText("$ 0");
                    }

                    View.superMainLayout.show(View.superMainPanel, "LogIn");
                    LogIn.emailInput.setText("");
                    LogIn.passwordInput.setText("");
                    ControlPanel.btnNumber = 0;
                }
            }   
        });
    }
}
