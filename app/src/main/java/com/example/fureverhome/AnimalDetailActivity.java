package com.example.fureverhome;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class AnimalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainScroll), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Animal animal = (Animal) getIntent().getSerializableExtra("animal");

        if (animal != null) {
            // Set up ViewPager for image carousel
            ViewPager2 viewPager = findViewById(R.id.imageCarousel);
            List<Integer> imageList = animal.getImageList();
            viewPager.setAdapter(new ImageCarouselAdapter(imageList));

            // Set up text fields
            ((TextView) findViewById(R.id.name)).setText("Name: " + animal.getName());
            ((TextView) findViewById(R.id.species)).setText("Species: " + animal.getSpecies());
            ((TextView) findViewById(R.id.breed)).setText("Breed: " + animal.getBreed());
            ((TextView) findViewById(R.id.size)).setText("Size: " + animal.getSize());
            ((TextView) findViewById(R.id.age)).setText("Age: " + animal.getAge());
            ((TextView) findViewById(R.id.gender)).setText("Gender: " + animal.getGender());
            ((TextView) findViewById(R.id.location)).setText("Location: " + animal.getLocation());
            ((TextView) findViewById(R.id.status)).setText("Status: " + animal.getStatus());
            ((TextView) findViewById(R.id.description)).setText(animal.getDescription());
        }
    }
}
