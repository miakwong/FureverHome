package com.example.fureverhome.ui.community;

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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Discussion;
import com.example.fureverhome.model.DiscussionUtils;

import java.util.ArrayList;
import java.util.List;

public class DiscussionsFragment extends Fragment  {
    private RecyclerView recyclerView;
    private DiscussionAdapter discussionAdapter;
    private List<Discussion> discussionList = new ArrayList<>();
    private List<Discussion> allDiscussionList = new ArrayList<>(); // Store the original list of discussions

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_community, container, false);

        // Set discussion generate button
        ImageButton generateButton = root.findViewById(R.id.generateButton);
        generateButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DiscussionGenerateActivity.class); // 改成你的页面类
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
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.discussion_filter_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();

            // Buttons from discussion_filter_dialog
            RadioGroup discussionTypeRadioGroup = dialogView.findViewById(R.id.discussionTypeRadioGroup);
            Button okBtn = dialogView.findViewById(R.id.okButton);
            Button resetBtn = dialogView.findViewById(R.id.resetButton);

            // OK button
            okBtn.setOnClickListener(btn -> {
                int selectedRadioId = discussionTypeRadioGroup.getCheckedRadioButtonId();
                String selectedDiscussionType = "";

                if (selectedRadioId == R.id.radio_adoption) {
                    selectedDiscussionType = "Adoption";
                } else if (selectedRadioId == R.id.radio_seminar) {
                    selectedDiscussionType = "Seminar";
                } else if (selectedRadioId == R.id.radio_clinic) {
                    selectedDiscussionType = "Clinic";
                } else if (selectedRadioId == R.id.radio_workshop) {
                    selectedDiscussionType = "Workshop";
                } else if (selectedRadioId == R.id.radio_volunteer) {
                    selectedDiscussionType = "Volunteer";
                }


                // Apply filter
                filterTasksByType(selectedDiscussionType);
                dialog.dismiss(); // Close dialog
            });

            // Reset Button
            resetBtn.setOnClickListener(btn -> {
                resetFilters(); // Reset to the original list of discussions
                dialog.dismiss();
            });

            dialog.show();

        });


        // Set RecyclerView
        recyclerView = root.findViewById(R.id.discussionTasksRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Use DiscussionUtils to load the discussions
        discussionList = DiscussionUtils.loadDiscussions();

        // Save the original list of discussions
        allDiscussionList.addAll(discussionList);

        discussionAdapter = new DiscussionAdapter(discussionList, requireContext());
        recyclerView.setAdapter(discussionAdapter);


        // Set SearchView for searching discussions by name
        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;   // Prdiscussion enter pressed
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDiscussions(newText);  // Filter discussions when text changes
                return false;
            }
        });

        return root;
    }

    private void filterDiscussions(String query) {
        List<Discussion> filteredList = new ArrayList<>();

        // Lowercase search text
        String queryLowerCase = query.toLowerCase();

        for (Discussion discussion : allDiscussionList) {
            if (discussion.getTitle().toLowerCase().contains(queryLowerCase)) {
                filteredList.add(discussion);
            }
        }

        // Update discussionAdapter
        if (discussionAdapter != null) {
            discussionAdapter.updateDiscussionList(filteredList);
        }
    }
    // Filter tasks by type
    private void filterTasksByType(String taskType) {
        List<Discussion> filteredList = new ArrayList<>();

        if (taskType == null || taskType.isEmpty()) {
            // No filter applied, show all tasks
            filteredList.addAll(allDiscussionList);
        } else {
            for (Discussion discussion : allDiscussionList) {
                if (discussion.getType().equalsIgnoreCase(taskType)) {
                    filteredList.add(discussion);
                }
            }
        }

        // Update the adapter with the filtered list
        DiscussionAdapter adapter = (DiscussionAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.updateDiscussionList(filteredList);
        }
    }

    // Reset to show all discussions
    private void resetFilters() {
        // Reset filters, show all discussions
        List<Discussion> resetList = new ArrayList<>(allDiscussionList);  // Make sure discussionList is not modified by filters

        // Update the adapter with the full discussion list
        DiscussionAdapter adapter = (DiscussionAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.updateDiscussionList(resetList);  // Reset to original discussion list
        }
    }
}

