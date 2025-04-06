package com.example.fureverhome.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fureverhome.databinding.FragmentTasksBinding;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Example UI interaction
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
