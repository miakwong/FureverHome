package com.example.fureverhome.ui.tasks;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Task;

public class TaskDetailsActivity extends AppCompatActivity {

    private ImageView taskIcon;
    private TextView titleText, typeText, postedDateText, startDateText, endDateText,
            durationText, locationText, organizerText, descriptionText;
    private Button applyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        // Bind views
        ImageView backButton = findViewById(R.id.backButton);

        // Set back button click listener
        backButton.setOnClickListener(v -> {
            finish();  // This will simulate the back press
        });

        //Get task item
        Task task = (Task) getIntent().getSerializableExtra("task");

        //Bind views
        taskIcon = findViewById(R.id.taskIcon);
        titleText = findViewById(R.id.titleText);
        typeText = findViewById(R.id.typeText);
        postedDateText = findViewById(R.id.postedDateText);
        startDateText = findViewById(R.id.startDateText);
        endDateText = findViewById(R.id.endDateText);
        durationText = findViewById(R.id.durationText);
        locationText = findViewById(R.id.locationText);
        organizerText = findViewById(R.id.organizerText);
        descriptionText = findViewById(R.id.descriptionText);
        applyBtn = findViewById(R.id.applyButton);

        //task data
        if (task != null) {
            taskIcon.setImageResource(task.getImageResId());
            titleText.setText(task.getTitle());
            typeText.setText(task.getTaskType());
            postedDateText.setText(task.getPostedDate());
            startDateText.setText(task.getStartDate());
            endDateText.setText(task.getEndDate());
            durationText.setText(task.getDuration());
            locationText.setText(task.getLocation());
            organizerText.setText(task.getOrganizer());
            descriptionText.setText(task.getDescription());
        }

        applyBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Applied to task", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
