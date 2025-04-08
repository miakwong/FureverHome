package com.example.fureverhome.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Event;
import com.example.fureverhome.model.EventUtils;
import com.example.fureverhome.ui.events.EventAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment  {
    private RecyclerView recyclerView;
    private List<Event> eventList = new ArrayList<>();
    private List<Event> allEventList = new ArrayList<>(); // Store the original list of tasks

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        // Set event generate button
        ImageButton generateButton = root.findViewById(R.id.generateButton);
        generateButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), EventGenerateActivity.class); // 改成你的页面类
            startActivity(intent);
        });


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

        // Set RecyclerView
        recyclerView = root.findViewById(R.id.eventTasksRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Use TaskUtils to load the tasks
        eventList = EventUtils.loadEvents();

        // Save the original list of tasks
        allEventList.addAll(eventList);

        EventAdapter adapter = new EventAdapter(eventList, requireContext());
        recyclerView.setAdapter(adapter);

        return root;
    }

    // Filter tasks by type
    private void filterTasksByType(String taskType) {
        List<Event> filteredList = new ArrayList<>();

        if (taskType == null || taskType.isEmpty()) {
            // No filter applied, show all tasks
            filteredList.addAll(allEventList);
        } else {
            for (Event event : allEventList) {
                if (event.getEventType().equalsIgnoreCase(taskType)) {
                    filteredList.add(event);
                }
            }
        }

        // Update the adapter with the filtered list
        EventAdapter adapter = (EventAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.updateEventList(filteredList);
        }
    }

    // Reset to show all tasks
    private void resetFilters() {
        // Reset filters, show all tasks
        List<Event> resetList = new ArrayList<>(allEventList);  // Make sure eventList is not modified by filters

        // Update the adapter with the full task list
        EventAdapter adapter = (EventAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.updateEventList(resetList);  // Reset to original task list
        }
    }
}

