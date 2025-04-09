package com.example.fureverhome;


import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.fureverhome.ui.shelter_management.Animal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String FILE_NAME = "animals.txt";

    public static void saveAnimalList(List<Animal> animals, Context context) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(animals);

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            osw.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Animal> loadAnimalList(Context context) {
        Gson gson = new Gson();
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis)) {
            Type type = new TypeToken<ArrayList<Animal>>(){}.getType();
            return gson.fromJson(isr, type);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list if there's an issue
        }
    }
}
