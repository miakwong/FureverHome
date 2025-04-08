package com.example.fureverhome.ui.events;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Event;
import com.example.fureverhome.model.EventUtils;

import java.util.List;

public class EventDetailsActivity extends AppCompatActivity{
    private ImageView eventIcon;
    private TextView titleText, typeText, postedDateText, startDateText,
            durationText, locationText, organizerText, descriptionText;
    private Button applyBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Bind views
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish()); // Go back to previous screen

        eventIcon = findViewById(R.id.eventIcon);
        titleText = findViewById(R.id.titleText);
        typeText = findViewById(R.id.typeText);
        postedDateText = findViewById(R.id.postedDateText);
        startDateText = findViewById(R.id.startDateText);
        durationText = findViewById(R.id.durationText);
        locationText = findViewById(R.id.locationText);
        organizerText = findViewById(R.id.organizerText);
        descriptionText = findViewById(R.id.descriptionText);
        applyBtn = findViewById(R.id.applyButton);

        // Get eventId from the Intent
        Event event = (Event) getIntent().getSerializableExtra("event");

        // Load events (using EventUtils)
        List<Event> allEvents = EventUtils.loadEvents();

        // Find the event by ID
//        Event event = EventUtils.getEventById(allEvents, eventId);

        if (event != null) {
            eventIcon.setImageResource(event.getImageId());
            titleText.setText(event.getTitle());
            typeText.setText(event.getEventType());
            postedDateText.setText(event.getPostedDate());
            startDateText.setText(event.getStartDate());
            durationText.setText(event.getDuration());
            locationText.setText(event.getLocation());
            organizerText.setText(event.getOrganizer());
            descriptionText.setText(event.getDescription());
        }

        applyBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Applied to event", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
