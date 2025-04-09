package com.example.fureverhome.ui.shelter_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fureverhome.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnimalAddActivity extends AppCompatActivity {

    private EditText editName, editSpecies, editBreed, editColour, editSize,
            editAge, editGender, editLocation, editStatus, editDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_add);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        // Link input fields
        editName = findViewById(R.id.editName);
        editSpecies = findViewById(R.id.editSpecies);
        editBreed = findViewById(R.id.editBreed);
        editColour = findViewById(R.id.editColour);
        editSize = findViewById(R.id.editSize);
        editAge = findViewById(R.id.editAge);
        editGender = findViewById(R.id.editGender);
        editLocation = findViewById(R.id.editLocation);
        editStatus = findViewById(R.id.editStatus);
        editDescription = findViewById(R.id.editDescription);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            String id = UUID.randomUUID().toString();
            String name = editName.getText().toString().trim();
            String species = editSpecies.getText().toString().trim();
            String breed = editBreed.getText().toString().trim();
            String colour = editColour.getText().toString().trim();
            String size = editSize.getText().toString().trim();
            int age = Integer.parseInt(editAge.getText().toString().trim());
            String gender = editGender.getText().toString().trim();
            String location = editLocation.getText().toString().trim();
            String status = editStatus.getText().toString().trim();
            String description = editDescription.getText().toString().trim();

            List<Integer> images = new ArrayList<>();
            images.add(R.drawable.ic_imageplaceholder); // Placeholder

            Animal newAnimal = new Animal(name, species, breed, colour, size, age, gender, images, location, status, description);
            newAnimal.setId(id);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newAnimal", newAnimal);
            setResult(RESULT_OK, resultIntent);
            Toast.makeText(this, "Animal added!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
