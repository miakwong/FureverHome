package com.example.fureverhome.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fureverhome.databinding.FragmentDashboardBinding;
import android.content.Intent;
import com.example.fureverhome.AnimalListings;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // UI interaction
        binding.getRoot().setOnClickListener(v ->
                v.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS)
        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
