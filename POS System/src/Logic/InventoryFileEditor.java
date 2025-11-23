package Logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.Product;

public class InventoryFileEditor {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static HashMap<Integer, Product> InventoryList = new HashMap<>();

    public static void dataInitializer() {
        try {
            InventoryFileEditor.inventoryReader();
        } catch (IOException e) {
            System.err.println("Data cannot be loaded");
            e.printStackTrace();
            return;
        }

    }

    //read file
    private static HashMap<Integer, Product> inventoryReader() throws IOException {
        try (InputStream inputStream = InventoryFileEditor.class.getResourceAsStream("/Data/inventory.json")) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<HashMap<Integer, Product>>() {}.getType();
            InventoryList = gson.fromJson(reader, type);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return InventoryList;
    }
}

