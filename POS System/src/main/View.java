package main;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import systemgui.LogIn;
import systemgui.PosGui;

public class View extends JFrame {

    public static CardLayout superMainLayout;
    public static JPanel superMainPanel;

    public View(){
        initComponents();
    }

    private void initComponents(){

        SwingUtilities.invokeLater(() -> {

            superMainLayout = new CardLayout();
            superMainPanel = new JPanel(superMainLayout);

            // Sales Man Login
            LogIn logIn = new LogIn();
            superMainPanel.add(logIn, "LogIn");

            // Login Btn
            LogIn.alertLabel.setVisible(false);
            LogIn.getLoginBtn().addActionListener(event -> {
                
                // String accessCode = LogIn.emailInput.getText();
                // String password = new String(LogIn.passwordInput.getPassword());

                // if (accessCode.isEmpty() || password.isEmpty()) {
                //     LogIn.alertLabel.setText("Please fill the fields");
                //     LogIn.alertLabel.setVisible(true);
                //     LogIn.alertLabel.revalidate();
                //     LogIn.alertLabel.repaint();
                //     return;
                // }
                
                // Users user = Authentication.userAuthentication(accessCode, password);

                // if (user == null) {
                //     LogIn.alertLabel.setText("Email or password isn't correct!");
                //     LogIn.alertLabel.setVisible(true);
                //     LogIn.alertLabel.revalidate();
                //     LogIn.alertLabel.repaint();
                // }

                // else {
                //     LogIn.alertLabel.setVisible(false);
                //     ControlPanel controlPanel = new ControlPanel(user);
                //     superMainPanel.add(controlPanel, "ControlPanel");
                //     superMainLayout.show(superMainPanel, "ControlPanel");
                // }

                PosGui posGui = new PosGui();
                superMainPanel.add(posGui, "PosGui");
                superMainLayout.show(superMainPanel, "PosGui");

            });

            add(superMainPanel);
            setSize(1366,768);
            setTitle("POS System");
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        });
    }
    
}
