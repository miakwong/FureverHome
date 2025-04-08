package com.example.fureverhome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalListings extends AppCompatActivity {

    private MyAdapter adapter;
    private List<Animal> animalList;
    private List<Animal> filteredList;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_listings);

        searchView = findViewById(R.id.searchView);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        View drawerView = navigationView.getHeaderView(0); // or navigationView.getChildAt(0)
        if (drawerView == null) drawerView = navigationView;

        Button applyBtn = drawerView.findViewById(R.id.applyFiltersButton);
        applyBtn.setOnClickListener(v -> {
            applyFilters();
            drawerLayout.closeDrawer(GravityCompat.START);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar2);
        topAppBar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(!item.isChecked());
            applyFilters();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVerticalScrollBarEnabled(true);

        animalList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Example animals
        animalList.add(new Animal("Larry", "Lizard", "Chinese Water Dragon", "Green", "Large", 5,
                "Male", List.of(R.drawable.larry), "Kelowna SPCA", "Available", "A playful and curious lizard."));
        animalList.add(new Animal("Molly", "Dog", "Golden Retriever", "Yellow", "Large", 2, "Female", List.of(R.drawable.molly), "Kelowna SPCA", "Pending", "An adorable girl"));
        animalList.add(new Animal("Bentley", "Cat", "short hair mix", "Grey and White", "Medium", 7, "Male", List.of(R.drawable.bentley), "Peachland SPCA", "Adopted", "A playful young man"));
        animalList.add(new Animal("Poppy", "Snake", "Ball Python", "Brown and Black", "Medium", 14, "Female", List.of(R.drawable.poppy), "Vernon SPCA", "Adopted", "An adventurous spirit"));
        animalList.add(new Animal("rocky", "Dog", "Golden Retriever", "Yellow", "Large", 0, "Male",List.of(R.drawable.rocky), "Vernon SPCA", "Available", "Playful and rambunctious"));
        animalList.add(new Animal("Sunny", "Horse", "Thoroughbred", "Red", "Large", 3, "Male", List.of(R.drawable.sunny), "Kelowna Farm Rescue", "Available", "Calm and steady. Great for kids"));
        animalList.add(new Animal("Tux", "Cat", "short hair mix", "Black and White", "Medium", 1, "Male", List.of(R.drawable.tux), "Vernon SPCA", "Pending", "Will make friends with your neighbours too"));

        filteredList.addAll(animalList);
        adapter = new MyAdapter(getApplicationContext(), filteredList);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilters();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilters();
                return true;
            }
        });
    }

    private void applyFilters() {
        filteredList.clear();

        // Access checkboxes from the NavigationView
        View header = navigationView.getHeaderView(0); // or use getChildAt(0) if header isn't used
        if (header == null) header = navigationView; // fallback

        // --- Collect checked values ---
        Set<String> selectedSpecies = new HashSet<>();
        if (((CheckBox) header.findViewById(R.id.filter_species_dog)).isChecked()) selectedSpecies.add("dog");
        if (((CheckBox) header.findViewById(R.id.filter_species_cat)).isChecked()) selectedSpecies.add("cat");
        if (((CheckBox) header.findViewById(R.id.filter_species_bird)).isChecked()) selectedSpecies.add("bird");
        if (((CheckBox) header.findViewById(R.id.filter_species_reptile)).isChecked()) selectedSpecies.add("reptile");
        if (((CheckBox) header.findViewById(R.id.filter_species_rodent)).isChecked()) selectedSpecies.add("rodent");
        if (((CheckBox) header.findViewById(R.id.filter_species_horse)).isChecked()) selectedSpecies.add("horse");
        if (((CheckBox) header.findViewById(R.id.filter_species_other)).isChecked()) selectedSpecies.add("other");

        Set<String> selectedSizes = new HashSet<>();
        if (((CheckBox) header.findViewById(R.id.filter_large)).isChecked()) selectedSizes.add("large");
        if (((CheckBox) header.findViewById(R.id.filter_medium)).isChecked()) selectedSizes.add("medium");
        if (((CheckBox) header.findViewById(R.id.filter_small)).isChecked()) selectedSizes.add("small");

        Set<String> selectedColours = new HashSet<>();
        if (((CheckBox) header.findViewById(R.id.filter_black)).isChecked()) selectedColours.add("black");
        if (((CheckBox) header.findViewById(R.id.filter_white)).isChecked()) selectedColours.add("white");
        if (((CheckBox) header.findViewById(R.id.filter_brown)).isChecked()) selectedColours.add("brown");
        if (((CheckBox) header.findViewById(R.id.filter_red)).isChecked()) selectedColours.add("red");
        if (((CheckBox) header.findViewById(R.id.filter_blue)).isChecked()) selectedColours.add("blue");
        if (((CheckBox) header.findViewById(R.id.filter_green)).isChecked()) selectedColours.add("green");
        if (((CheckBox) header.findViewById(R.id.filter_silver)).isChecked()) selectedColours.add("silver");
        if (((CheckBox) header.findViewById(R.id.filter_colour_other)).isChecked()) selectedColours.add("other");

        Set<String> selectedGenders = new HashSet<>();
        if (((CheckBox) header.findViewById(R.id.filter_male)).isChecked()) selectedGenders.add("male");
        if (((CheckBox) header.findViewById(R.id.filter_female)).isChecked()) selectedGenders.add("female");
        if (((CheckBox) header.findViewById(R.id.filter_unknown)).isChecked()) selectedGenders.add("unknown");

        Set<String> selectedLocations = new HashSet<>();
        if (((CheckBox) header.findViewById(R.id.filter_KelownaSPCA)).isChecked()) selectedLocations.add("kelowna spca");
        if (((CheckBox) header.findViewById(R.id.filter_PentictonSPCA)).isChecked()) selectedLocations.add("penticton spca");
        if (((CheckBox) header.findViewById(R.id.filter_VernonSPCA)).isChecked()) selectedLocations.add("vernon spca");

        Set<String> selectedStatus = new HashSet<>();
        if (((CheckBox) header.findViewById(R.id.filter_Available)).isChecked()) selectedStatus.add("available");
        if (((CheckBox) header.findViewById(R.id.filter_Pending)).isChecked()) selectedStatus.add("pending");
        if (((CheckBox) header.findViewById(R.id.filter_Adopted)).isChecked()) selectedStatus.add("adopted");

        // Age ranges
        boolean under1 = ((CheckBox) header.findViewById(R.id.filter_under_1)).isChecked();
        boolean age2to4 = ((CheckBox) header.findViewById(R.id.filter_2_to_4)).isChecked();
        boolean age5to8 = ((CheckBox) header.findViewById(R.id.filter_5_to_8)).isChecked();
        boolean age9plus = ((CheckBox) header.findViewById(R.id.filter_9_or_more)).isChecked();

        // --- Filter list ---
        for (Animal animal : animalList) {
            boolean match = true;

            if (!selectedSpecies.isEmpty() && !selectedSpecies.contains(animal.getSpecies().toLowerCase())) {
                match = false;
            }

            if (!selectedSizes.isEmpty() && !selectedSizes.contains(animal.getSize().toLowerCase())) {
                match = false;
            }

            if (!selectedColours.isEmpty() && !selectedColours.contains(animal.getColour().toLowerCase())) {
                match = false;
            }

            if (!selectedGenders.isEmpty() && !selectedGenders.contains(animal.getGender().toLowerCase())) {
                match = false;
            }

            if (!selectedLocations.isEmpty() && !selectedLocations.contains(animal.getLocation().toLowerCase())) {
                match = false;
            }

            if (!selectedStatus.isEmpty() && !selectedStatus.contains(animal.getStatus().toLowerCase())) {
                match = false;
            }

            // Age filtering
            int age = animal.getAge();
            if ((under1 || age2to4 || age5to8 || age9plus)) {
                if (!(under1 && age <= 1 ||
                        age2to4 && age >= 2 && age <= 4 ||
                        age5to8 && age >= 5 && age <= 8 ||
                        age9plus && age >= 9)) {
                    match = false;
                }
            }

            if (match) filteredList.add(animal);
        }

        adapter.notifyDataSetChanged();
    }
}

