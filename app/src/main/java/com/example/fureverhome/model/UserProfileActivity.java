package com.example.fureverhome.model;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import android.content.Intent;

public class UserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        MaterialButton trustBtn = findViewById(R.id.btn_leave_review);
        trustBtn.setOnClickListener(v -> startActivity(new Intent(this, ReviewActivity.class)));

        MaterialButton dashboardBtn = findViewById(R.id.btn_back_to_dashboard);
        dashboardBtn.setOnClickListener(v -> {
            //Intent intent = new Intent(ReviewActivity.this, MainActivity.class); // Replace with actual dashboard class
            //   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            // startActivity(intent);
            // finish(); // Optional: close current screen
        });
    }
}
