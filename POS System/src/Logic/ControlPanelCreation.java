package Logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.View;
import systemgui.ControlPanel;
import systemgui.PosGui;

public class ControlPanelCreation {

    public static int btnNumber = ControlPanel.btnNumber;
    private JButton[] btnPanel;


    public JButton[] generateAdminPanel(){

        // Create Buttons Panel
        btnPanel = new JButton[btnNumber];
        String[] texts = {"Manage Users", "Inventory", "Orders History", "Close Operations"};
        String[] path = {"/Images/UserIcon.png", "/Images/AdminImages/Inventory.png", "/Images/AdminImages/Orders.png", "/Images/AdminImages/Close.png"};
        buttonEstablisher(btnPanel, texts, path);

        // Sales Panel
        JPanel salesPanel = new JPanel();
        salesPanel.setBackground(new Color(0xE8CEB0));
        salesPanel.setBorder(new EmptyBorder(0,0,60,0));
        ControlPanel.controlPanelMainPanel.add(salesPanel, BorderLayout.SOUTH);

        // Enter to Sales Button
        JButton salesBtn = new JButton("Enter to Sales");
        salesBtn.setPreferredSize(new Dimension(300,100));
        salesBtn.setBorder(new EmptyBorder(20, 10, 20,10));
        salesBtn.setFont(salesBtn.getFont().deriveFont(32f));
        salesBtn.setFocusable(false);
        salesBtn.setBackground(new Color(0X00BF63));
        salesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        salesPanel.add(salesBtn);

        salesBtn.addActionListener(e-> {
            PosGui pos = new PosGui();
            View.superMainPanel.add(pos, "POS");
            View.superMainLayout.show(View.superMainPanel, "POS");
        });

        return btnPanel;
    }

    public JButton[] generateUserPanel(){

        btnPanel = new JButton[btnNumber];
        String[] texts = {"Enter to Sales", "Orders History", "Close Operations"};
        String[] path = {"/Images/AdminImages/EnterSales.png", "/Images/AdminImages/Inventory.png", "/Images/AdminImages/Close.png"};
        buttonEstablisher(btnPanel, texts, path);

        return btnPanel;
    }

    private void buttonEstablisher(JButton[] btnPanel, String[] texts, String[] path){

        for (int i = 0; i < btnPanel.length; i++) {
            btnPanel[i] = new JButton();
            btnCustomizer(btnPanel[i]);
            btnPanel[i].setText(texts[i]);
            btnPanel[i].setFont(btnPanel[i].getFont().deriveFont(24f));
            btnPanel[i].setIcon(imgCustomizer(path[i]));
            btnPanel[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            btnPanel[i].setHorizontalTextPosition(SwingConstants.CENTER);
            btnPanel[i].setVerticalAlignment(SwingConstants.CENTER);
        }

    };

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
