package com.example.fureverhome;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AnimalListings extends AppCompatActivity {

    private MyAdapter adapter;
    private List<Animal> animalList;
    private List<Animal> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_listings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        SearchView searchView = findViewById(R.id.searchView);

        animalList = new ArrayList<>();
        filteredList = new ArrayList<>();


        animalList.add(new Animal("Larry", "Lizard", "Chinese Water Dragon", "Green", "Large", 5, "Male", R.drawable.larry, "Kelowna SPCA", "Available"));
        animalList.add(new Animal("Molly", "Dog", "Golden Retriever", "Yellow", "Large", 2, "Female", R.drawable.molly, "Kelowna SPCA", "Pending"));
        animalList.add(new Animal("Bentley", "Cat", "short hair mix", "Grey and White", "Medium", 7, "Male", R.drawable.bentley, "Peachland SPCA", "Adopted"));
        animalList.add(new Animal("Poppy", "Snake", "Ball Python", "Brown and Black", "Medium", 14, "Female", R.drawable.poppy, "Vernon SPCA", "Adopted"));
        animalList.add(new Animal("rocky", "Dog", "Golden Retriever", "Yellow", "Large", 0, "Male", R.drawable.rocky, "Vernon SPCA", "Available"));
        animalList.add(new Animal("Sunny", "Horse", "Thoroughbred", "Red", "Large", 3, "Male", R.drawable.sunny, "Kelowna Farm Rescue", "Available"));
        animalList.add(new Animal("Tux", "Cat", "short hair mix", "Black and White", "Medium", 1, "Male", R.drawable.tux, "Vernon SPCA", "Pending"));

        filteredList.addAll(animalList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(getApplicationContext(), filteredList);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterAnimals(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterAnimals(newText);
                return true;
            }
        });
    }

    private void filterAnimals(String query) {
        filteredList.clear();
        String lowerQuery = query.toLowerCase();

        for (Animal animal : animalList) {
            if (animal.getName().toLowerCase().contains(lowerQuery) ||
                    animal.getSpecies().toLowerCase().contains(lowerQuery) ||
                    animal.getBreed().toLowerCase().contains(lowerQuery) ||
                    animal.getColour().toLowerCase().contains(lowerQuery) ||
                    String.valueOf(animal.getAge()).contains(lowerQuery) ||
                    animal.getSize().toLowerCase().contains(lowerQuery) ||
                    animal.getLocation().toLowerCase().contains(lowerQuery)) {
                filteredList.add(animal);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
