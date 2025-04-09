package com.example.fureverhome.ui.pets;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fureverhome.R;

public class PetDetailsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private PetImageAdapter petImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        int[] petImageResIds = getIntent().getIntArrayExtra("galleryResIds");

        if (petImageResIds == null || petImageResIds.length == 0) {
            finish();
            return;
        }

        // Initialize ViewPager2
        viewPager = findViewById(R.id.viewPager);
        petImageAdapter = new PetImageAdapter(petImageResIds);
        viewPager.setAdapter(petImageAdapter);

        // Start the auto sliding of images
        startAutoSlide();
    }

    // Start auto slide of images every 2 seconds
    private void startAutoSlide() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final int totalItems = petImageAdapter.getItemCount();

        Runnable runnable = new Runnable() {
            int currentItem = 0;

            @Override
            public void run() {
                currentItem = (currentItem + 1) % totalItems;
                viewPager.setCurrentItem(currentItem, true);
                handler.postDelayed(this, 2500);
            }
        };

        handler.postDelayed(runnable, 2500);
    }

    private class PetImageAdapter extends FragmentStateAdapter {

        private int[] imageResIds;

        public PetImageAdapter(int[] imageResIds) {
            super(PetDetailsActivity.this);
            this.imageResIds = imageResIds;
        }

        @Override
        public Fragment createFragment(int position) {
            // Return a new ImageFragment for each image
            return ImageFragment.newInstance(imageResIds[position]);
        }

        @Override
        public int getItemCount() {
            return imageResIds.length;
        }
    }
}
