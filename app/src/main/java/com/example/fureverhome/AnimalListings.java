package com.example.fureverhome;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AnimalListings extends AppCompatActivity {

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

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Larry", "Lizard", "Chinese Water Dragon", "Green", "Large", 5, "Male", R.drawable.larry, "Kelowna SPCA", "Available"));
        animals.add(new Animal("Molly", "Dog", "Golden Retriever", "Yellow", "Large", 2, "Female", R.drawable.molly, "Kelowna SPCA", "Pending"));
        animals.add(new Animal("Bentley", "Cat", "short hair mix", "Grey and White", "Medium", 7, "Male", R.drawable.bentley, "Peachland SPCA", "Adopted"));
        animals.add(new Animal("Poppy", "Snake", "Ball Python", "Brown and Black", "Medium", 14, "Female", R.drawable.poppy, "Vernon SPCA", "Adopted"));
        animals.add(new Animal("rocky", "Dog", "Golden Retriever", "Yellow", "Large", 0, "Male", R.drawable.rocky, "Vernon SPCA", "Available"));
        animals.add(new Animal("Sunny", "Horse", "Thoroughbred", "Red", "Large", 3, "Male", R.drawable.sunny, "Kelowna Farm Rescue", "Available"));
        animals.add(new Animal("Tux", "Cat", "short hair mix", "Black and White", "Medium", 1, "Male", R.drawable.tux, "Vernon SPCA", "Pending"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), animals));
    }
}