package com.example.fureverhome.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fureverhome.databinding.FragmentShelterDashboardBinding;

public class ShelterDashboardFragment extends Fragment {

    private FragmentShelterDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShelterDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // No logic added for now. Just rendering the layout.
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
