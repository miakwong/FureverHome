package com.example.fureverhome.ui.pets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.fureverhome.R;

public class ImageFragment extends Fragment {

    private static final String ARG_IMAGE_RES_ID = "imageResId";

    public static ImageFragment newInstance(int imageResId) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView imageView = rootView.findViewById(R.id.imageView);
        int imageResId = getArguments().getInt(ARG_IMAGE_RES_ID);
        imageView.setImageResource(imageResId);

        // stop button
        ImageButton stopButton = rootView.findViewById(R.id.stopButton);
        stopButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().finish(); //back to dashboard
            }
        });

        return rootView;
    }

}
