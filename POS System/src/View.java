import java.awt.CardLayout;
import javax.swing.*;
import systemgui.ControlPanel;
import systemgui.LogIn;

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
            LogIn salesManLogIn = new LogIn();
            superMainPanel.add(salesManLogIn, "SalesManLogIn");

            // Login Btn
            LogIn.getLoginBtn().addActionListener(event -> {
                ControlPanel controlPanel = new ControlPanel();
                superMainPanel.add(controlPanel,"ControlPanel");
                superMainLayout.show(superMainPanel, "ControlPanel");
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
