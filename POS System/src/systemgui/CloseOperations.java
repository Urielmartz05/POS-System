package systemgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Order;

public class CloseOperations {

    public static JLabel productsAmount;
    public static JLabel salesAmount;
    public static JLabel totalKgLabel;
    public static List<Order> ordersList;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void closeOperations(){

        JPanel panel = closeOperationPanel();

        getSalesData();

        int answer = JOptionPane.showConfirmDialog(
            null, 
            panel,
            "Close Operations",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (answer == JOptionPane.OK_OPTION) {
            deleteData();

            productsAmount.setText("$ 0");
            totalKgLabel.setText("$ 0");
            salesAmount.setText("$ 0");
        }

        
    }

    private static void getSalesData(){

        List<Order> ordersList = new ArrayList<>();
        int totalProducts = 0;
        double totalSales = 0;
        double kgAmount = 0;

        // Get data from orders Json
        try (InputStream inputStream = CloseOperations.class.getResourceAsStream("/Data/orders.json")) {
            Reader reader = new InputStreamReader(inputStream);
            ordersList = gson.fromJson(reader, new TypeToken<List<Order>>(){}.getType());


        } catch (Exception e) {
            e.getStackTrace();
        }

        for (Order order : ordersList) {
            totalProducts += order.getQuantity();    
            

            if (order.getType().equals("Unit")) {
                totalProducts += order.getQuantity();
            }else{
                kgAmount += order.getQuantity();
            }
            
            totalSales += order.getTotal();
        }

        productsAmount.setText(String.valueOf(totalProducts));
        totalKgLabel.setText(String.valueOf(kgAmount));
        salesAmount.setText("$ " + String.valueOf(totalSales));
        
    }
    
    
    private static JPanel closeOperationPanel(){

        // Base Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 250));

        // Sales summary
        JLabel salesSummary = new JLabel("Sales Summary");
        salesSummary.setFont(salesSummary.getFont().deriveFont(24f));
        salesSummary.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(salesSummary, BorderLayout.NORTH);

        // Main Panel
        JPanel closeOperationPanel = new JPanel();
        closeOperationPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(closeOperationPanel);

        // Main left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(5,5,5,5));
        closeOperationPanel.add(leftPanel);

        // Main right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(5,5,5,5));
        closeOperationPanel.add(rightPanel);

        // Total products
        leftPanel.add(Box.createVerticalStrut(40));
        JLabel totalProducts = new JLabel("Total Products: ");
        totalProducts.setFont(totalProducts.getFont().deriveFont(20f));
        leftPanel.add(totalProducts);

        leftPanel.add(Box.createVerticalStrut(10));

        // Total Kg
        JLabel totalKg = new JLabel("Total Kg: ");
        totalKg.setFont(totalKg.getFont().deriveFont(20f));
        leftPanel.add(totalKg);

        leftPanel.add(Box.createVerticalStrut(10));

        // Total Sales
        JLabel totalSales = new JLabel("Total Sales: ");
        totalSales.setFont(totalSales.getFont().deriveFont(20f));
        leftPanel.add(totalSales);

        rightPanel.add(Box.createVerticalStrut(35));

        // Total products amount
        productsAmount = new JLabel("$ 0");
        productsAmount.setFont(productsAmount.getFont().deriveFont(20f));
        productsAmount.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(productsAmount);

        rightPanel.add(Box.createVerticalStrut(10));

        // Total Kg amount
        totalKgLabel = new JLabel("$ 0");
        totalKgLabel.setFont(totalKgLabel.getFont().deriveFont(20f));
        totalKgLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(totalKgLabel);

        rightPanel.add(Box.createVerticalStrut(10));

        // Total sales ammount
        salesAmount = new JLabel("$ 0");
        salesAmount.setFont(salesAmount.getFont().deriveFont(20f));
        salesAmount.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(salesAmount);

        // Sales alert
        JLabel alert = new JLabel("Closing operations all data will be deleted");
        alert.setHorizontalAlignment(SwingConstants.CENTER);
        alert.setFont(alert.getFont().deriveFont(12f));
        alert.setForeground(Color.RED);
        mainPanel.add(alert, BorderLayout.SOUTH);


        return mainPanel;
    }

    private static void deleteData(){
        
        // Get data from orders Json
        try (InputStream inputStream = CloseOperations.class.getResourceAsStream("/Data/orders.json")) {
            Reader reader = new InputStreamReader(inputStream);
            ordersList = gson.fromJson(reader, new TypeToken<List<Order>>(){}.getType());

        } catch (Exception e) {
            e.getStackTrace();
        }

        // Delete data from orders Json
        ordersList.clear();

        // Write data to orders Json
        try (Writer writer = new FileWriter("POS System/src/Data/orders.json")) {
            gson.toJson(ordersList, writer);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }


}
