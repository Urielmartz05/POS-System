package Controller;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Order;
import systemgui.OrdersHistoryPanel;

public class OrdersHistory {
    
    public static void ordersHistory(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ArrayList<Order> newOrders = SalesLogic.orders;

        // Read Data before write
        ArrayList<Order> history = ordersReader();
        
        // Add new orders to history
        if (history == null) {
            history = new ArrayList<>();
        }
        history.addAll(newOrders);
        
        // Update data in orders Json
        try (Writer writer = new FileWriter("POS System/src/Data/orders.json")) {
            gson.toJson(history, writer);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static ArrayList<Order> ordersReader(){

        ArrayList<Order> orders = new ArrayList<>();

        try (InputStream inputStream = OrdersHistory.class.getResourceAsStream("/Data/orders.json")) {
            if (inputStream != null) {
                Type type = new TypeToken<ArrayList<Order>>() {}.getType();
                InputStreamReader reader = new InputStreamReader(inputStream);
                orders = new Gson().fromJson(reader, type);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        if (orders == null) {
            orders = new ArrayList<>();
        }

        return orders;
    }

    public static void ordersReset(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter("POS System/src/Data/orders.json")) {
            gson.toJson(new ArrayList<Order>(), writer);
        } catch (Exception e) {
            e.getStackTrace();
        }

        DefaultTableModel model = (DefaultTableModel) OrdersHistoryPanel.table.getModel();
        model.setRowCount(0);

    }

}
