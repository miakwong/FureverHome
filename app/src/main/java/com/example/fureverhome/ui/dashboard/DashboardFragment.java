package com.example.fureverhome.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.fureverhome.R;
import com.example.fureverhome.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnShelter.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashboard_to_shelterDashboard);
        });

        binding.btnVolunteer.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashboard_to_volunteerDashboard);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
