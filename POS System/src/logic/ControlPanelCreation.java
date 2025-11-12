package logic;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ControlPanelCreation {

    public static int btnNumber = 4;
    private JButton[] btnPanel;
    private JPanel btnMainPanel;

    public JButton[] generatePanel(){

        btnPanel = new JButton[btnNumber];
        btnMainPanel = new JPanel();
        String[] texts = {"Manage Users", "Inventory", "Orders History", "Close Operations"};
        String[] path = {"/Images/UserIcon.png", "/Images/AdminImages/Inventory.png", "/Images/AdminImages/Orders.png", "/Images/AdminImages/Close.png"};

        for (int i = 0; i < btnPanel.length; i++) {
            btnPanel[i] = new JButton();
            btnPanel[i].setText(texts[i]);
            btnPanel[i].setFont(btnPanel[i].getFont().deriveFont(24f));
            btnPanel[i].setIcon(imgCustomizer(path[i]));
            btnPanel[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            btnPanel[i].setHorizontalTextPosition(SwingConstants.CENTER);
            btnPanel[i].setHorizontalAlignment(SwingConstants.CENTER);
            btnCustomizer(btnPanel[i]);
        }
        return btnPanel;
    }

    private void btnCustomizer(JButton btnPanel){
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setFocusable(false);
        btnPanel.setFocusPainted(false);
        btnPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private ImageIcon imgCustomizer(String path){
        ImageIcon imgGet = new ImageIcon(getClass().getResource(path));
        Image imgResize = imgGet.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(imgResize);
        return img;
    }

	
}
