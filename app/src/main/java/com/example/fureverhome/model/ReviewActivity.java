package com.example.fureverhome.model;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ReviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);  // FIRST load the layout

        MaterialButton btnBack = findViewById(R.id.btn_back_to_profile);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        TextView ratingValue = findViewById(R.id.rating_value);
        EditText detailInput = findViewById(R.id.review_detail);
        MaterialButton submitBtn = findViewById(R.id.submit_btn);


        btnBack.setOnClickListener(v -> finish());

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            ratingValue.setText("You're Rating: " + rating + " Stars");
        });

        submitBtn.setOnClickListener(v -> {
            String details = detailInput.getText().toString();
            float stars = ratingBar.getRating();
            Toast.makeText(this, "Review Submitted! Rating: " + stars + "\n" + details, Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
