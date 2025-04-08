package com.example.fureverhome.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Task;
import com.example.fureverhome.model.TaskUtils;

import java.util.List;
import java.util.ArrayList;

public class TasksFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new ArrayList<>();
    private List<Task> allTaskList = new ArrayList<>(); // Store the original list of tasks

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        // Set RecyclerView
        recyclerView = root.findViewById(R.id.volunteerTasksRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Use TaskUtils to load the tasks
        taskList = TaskUtils.loadTasks();

        // Save the original list of tasks
        allTaskList.addAll(taskList);

        taskAdapter = new TaskAdapter(taskList, requireContext());
        recyclerView.setAdapter(taskAdapter);

        // Set spinners
        Spinner spinnerDistance = root.findViewById(R.id.spinnerDistance);
        Spinner spinnerPostingDate = root.findViewById(R.id.spinnerPostingDate);

        // Distance Spinner
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new String[] {"Distance", "≤ 5 km", "≤ 10 km"}
        );
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistance.setAdapter(distanceAdapter);

        // Posting Date Spinner
        ArrayAdapter<String> postingAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new String[] {"Posting Date", "Past 3 days", "Past week", "Past month"}
        );
        postingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPostingDate.setAdapter(postingAdapter);

        // Filter setting
        ImageButton filterButton = root.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(v -> {
            // Inflate dialog layout
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.task_filter_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();

            // Buttons from task_filter_dialog
            RadioGroup taskTypeRadioGroup = dialogView.findViewById(R.id.taskTypeRadioGroup);
            Button okBtn = dialogView.findViewById(R.id.okButton);
            Button resetBtn = dialogView.findViewById(R.id.resetButton);

            // OK button
            okBtn.setOnClickListener(btn -> {
                int selectedRadioId = taskTypeRadioGroup.getCheckedRadioButtonId();
                String selectedTaskType = "";

                if (selectedRadioId == R.id.radio_foster) {
                    selectedTaskType = "Foster Care";
                } else if (selectedRadioId == R.id.radio_transport) {
                    selectedTaskType = "Animal Transport";
                } else if (selectedRadioId == R.id.radio_event) {
                    selectedTaskType = "Event Assistance";
                } else if (selectedRadioId == R.id.radio_shelter) {
                    selectedTaskType = "Shelter Support";
                } else if (selectedRadioId == R.id.radio_medical) {
                    selectedTaskType = "Medical Assistance";
                }

                // Apply filter
                filterTasksByType(selectedTaskType);
                dialog.dismiss(); // Close dialog
            });

            // Reset Button
            resetBtn.setOnClickListener(btn -> {
                resetFilters(); // Reset to the original list of tasks
                dialog.dismiss();
            });

            dialog.show();
        });

        // Set SearchView for searching tasks by name
        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;   // Prevent enter pressed
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTasks(newText);  // Filter tasks when text changes
                return false;
            }
        });

        return root;
    }

    private void filterTasks(String query) {
        List<Task> filteredList = new ArrayList<>();

        // Lowercase search text
        String queryLowerCase = query.toLowerCase();

        for (Task task : allTaskList) {
            if (task.getTitle().toLowerCase().contains(queryLowerCase)) {
                filteredList.add(task);
            }
        }

        // Update taskAdapter
        if (taskAdapter != null) {
            taskAdapter.updateTaskList(filteredList);
        }
    }

    // Filter tasks by type
    private void filterTasksByType(String taskType) {
        List<Task> filteredList = new ArrayList<>();

        if (taskType == null || taskType.isEmpty()) {
            // No filter applied, show all tasks
            filteredList.addAll(allTaskList);
        } else {
            for (Task task : allTaskList) {
                if (task.getTaskType().equalsIgnoreCase(taskType)) {
                    filteredList.add(task);
                }
            }
        }

        // Update the adapter with the filtered list
        TaskAdapter adapter = (TaskAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.updateTaskList(filteredList);
        }
    }

    // Reset to show all tasks
    private void resetFilters() {
        // Reset filters, show all tasks
        List<Task> resetList = new ArrayList<>(allTaskList);  // Make sure taskList is not modified by filters

        // Update the adapter with the full task list
        TaskAdapter adapter = (TaskAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.updateTaskList(resetList);  // Reset to original task list
        }
    }
}
