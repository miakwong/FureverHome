package com.example.fureverhome.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        //Set spinners
        Spinner spinnerDistance = root.findViewById(R.id.spinnerDistance);
        Spinner spinnerPostingDate = root.findViewById(R.id.spinnerPostingDate);

        //Distance Spinner
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new String[] {"All", "≤ 5 km", "≤ 10 km", "≥ 10 km"}
        );
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistance.setAdapter(distanceAdapter);

        //Posting Date
        ArrayAdapter<String> postingAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new String[] {"Today", "Past 3 days", "Past week", "Past month"}
        );
        postingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPostingDate.setAdapter(postingAdapter);

        //Set recyclerview
        recyclerView = root.findViewById(R.id.volunteerTasksRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Foster a Kitten", "Location: Downtown", "Duration: 3 Weeks", R.drawable.ic_task_placeholder,  "Foster Care"));
        taskList.add(new Task("Transport a Dog", "Location: Rutland", "Destination: Foster Home", R.drawable.ic_task_placeholder, "Animal Transport"));
        taskList.add(new Task("Help at Adoption Event", "Location: BC SPCA", "Time: Saturday 10 AM", R.drawable.ic_task_placeholder, "Event Help"));

        TaskAdapter adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);

        return root;
    }
}
