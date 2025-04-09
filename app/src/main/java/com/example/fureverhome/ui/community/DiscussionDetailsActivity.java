package com.example.fureverhome.ui.community;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Discussion;
import com.example.fureverhome.model.DiscussionUtils;

import java.util.List;

public class DiscussionDetailsActivity extends AppCompatActivity {
    private ImageView discussionIcon;
    private TextView titleText, typeText, postedDateText,descriptionText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_details);

        // Bind views
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish()); // Go back to previous screen

        discussionIcon = findViewById(R.id.eventIcon);
        titleText = findViewById(R.id.titleText);
        typeText = findViewById(R.id.typeText);
        postedDateText = findViewById(R.id.postedDateText);
        descriptionText = findViewById(R.id.descriptionText);

        // Get discussionId from the Intent
        Discussion discussion = (Discussion) getIntent().getSerializableExtra("discussion");

        // Load discussions (using DiscussionUtils)
        List<Discussion> allDiscussions = DiscussionUtils.loadDiscussions();

        // Find the discussion by ID
//        Discussion discussion = DiscussionUtils.getDiscussionById(allDiscussions, discussionId);

        if (discussion != null) {
            discussionIcon.setImageResource(discussion.getImageId());
            titleText.setText(discussion.getTitle());
            typeText.setText(discussion.getType());
            postedDateText.setText(discussion.getPostDate());
            descriptionText.setText(discussion.getDescription());
        }
    }
}
