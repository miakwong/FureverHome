package com.example.fureverhome.ui.shelter_management;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fureverhome.R;

public class AnimalUpdateActivity extends AppCompatActivity {

    private LinearLayout imagePreviewContainer;
    private Animal animal;

    private EditText editName, editSpecies, editBreed, editAge, editSize,
            editGender, editLocation, editStatus, editDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_update);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        animal = (Animal) getIntent().getSerializableExtra("animal");

        int size = (int) getResources().getDimension(R.dimen.image_preview_size);
        imagePreviewContainer = findViewById(R.id.imagePreviewContainer);

        // Find EditText views
        editName = findViewById(R.id.editName);
        editSpecies = findViewById(R.id.editSpecies);
        editBreed = findViewById(R.id.editBreed);
        editAge = findViewById(R.id.editAge);
        editSize = findViewById(R.id.editSize);
        editGender = findViewById(R.id.editGender);
        editLocation = findViewById(R.id.editLocation);
        editStatus = findViewById(R.id.editStatus);
        editDescription = findViewById(R.id.editDescription);

        // Prefill values
        editName.setText(animal.getName());
        editSpecies.setText(animal.getSpecies());
        editBreed.setText(animal.getBreed());
        editAge.setText(String.valueOf(animal.getAge()));
        editSize.setText(animal.getSize());
        editGender.setText(animal.getGender());
        editLocation.setText(animal.getLocation());
        editStatus.setText(animal.getStatus());
        editDescription.setText(animal.getDescription());

        // Load existing images
        for (int resId : animal.getImageList()) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(8, 0, 8, 0);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(resId);
            imagePreviewContainer.addView(imageView);
        }

        // Drag & Drop (Optional for now — limited support)
        findViewById(R.id.imageDropArea).setOnDragListener((v, event) -> {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                ClipData clipData = event.getClipData();
                if (clipData != null && clipData.getItemCount() > 0) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Drawable image = item.getUri() != null ? Drawable.createFromPath(item.getUri().getPath()) : null;
                        if (image != null) {
                            ImageView newImage = new ImageView(this);
                            LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(size, size);
                            newParams.setMargins(8, 0, 8, 0);
                            newImage.setLayoutParams(newParams);
                            newImage.setImageDrawable(image);
                            imagePreviewContainer.addView(newImage);
                            // Note: Doesn't persist in animal.getImageList() yet
                        }
                    }
                }
                return true;
            }
            return true;
        });

        // Save Changes logic (in-memory update only)
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            animal.setName(editName.getText().toString());
            animal.setSpecies(editSpecies.getText().toString());
            animal.setBreed(editBreed.getText().toString());
            animal.setAge(Integer.parseInt(editAge.getText().toString()));
            animal.setSize(editSize.getText().toString());
            animal.setGender(editGender.getText().toString());
            animal.setLocation(editLocation.getText().toString());
            animal.setStatus(editStatus.getText().toString());
            animal.setDescription(editDescription.getText().toString());

            // Show confirmation
            Toast.makeText(this, "Animal updated locally!", Toast.LENGTH_SHORT).show();

            // Return updated animal to previous screen
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedAnimal", animal);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
