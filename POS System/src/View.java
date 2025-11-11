import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import systemgui.SalesManLogIn;

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
            SalesManLogIn salesManLogIn = new SalesManLogIn();
            superMainPanel.add(salesManLogIn, "SalesManLogIn");

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
