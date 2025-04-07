package com.example.fureverhome.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.ui.tasks.TaskDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class VolunteerDashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<DashboardTask> taskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_volunteer_dashboard, container, false);

        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);

        // Load sample tasks (you can replace this with real data)
        loadTasks();

        // Set click listeners for My Tasks section and Feedback section
        View tasksSection = rootView.findViewById(R.id.tasksSection);
        View feedbackSection = rootView.findViewById(R.id.feedbackSection);

        // Set click listeners for the entire sections
        tasksSection.setOnClickListener(v -> toggleTasksSection());
        feedbackSection.setOnClickListener(v -> toggleFeedbackSection());

        // Set click listeners for the toggle icons
        ImageView tasksToggleIcon = rootView.findViewById(R.id.tasksToggleIcon);
        ImageView feedbackToggleIcon = rootView.findViewById(R.id.feedbackToggleIcon);

        tasksToggleIcon.setOnClickListener(v -> toggleTasksSection());
        feedbackToggleIcon.setOnClickListener(v -> toggleFeedbackSection());

        return rootView;
    }

    // Toggle My Tasks Section
    private void toggleTasksSection() {
        View rootView = getView();
        if (rootView == null) return;

        LinearLayout tasksListContainer = rootView.findViewById(R.id.tasksListContainer);
        ImageView tasksToggleIcon = rootView.findViewById(R.id.tasksToggleIcon);

        if (tasksListContainer.getVisibility() == View.GONE) {
            tasksListContainer.setVisibility(View.VISIBLE);
            tasksToggleIcon.setImageResource(R.drawable.ic_arrow_up);
        } else {
            tasksListContainer.setVisibility(View.GONE);
            tasksToggleIcon.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    // Toggle Feedback Section
    private void toggleFeedbackSection() {
        View rootView = getView();
        if (rootView == null) return;

        LinearLayout feedbackContentContainer = rootView.findViewById(R.id.feedbackContentContainer);
        ImageView feedbackToggleIcon = rootView.findViewById(R.id.feedbackToggleIcon);

        if (feedbackContentContainer.getVisibility() == View.GONE) {
            feedbackContentContainer.setVisibility(View.VISIBLE);
            feedbackToggleIcon.setImageResource(R.drawable.ic_arrow_up);
        } else {
            feedbackContentContainer.setVisibility(View.GONE);
            feedbackToggleIcon.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    // Load sample tasks (replace with real data source)
    private void loadTasks() {
        taskList.add(new DashboardTask("1", "Foster Care", "In Progress"));
        taskList.add(new DashboardTask("2", "Adoption Event Helper", "Completed"));
        taskList.add(new DashboardTask("3", "Animal Transport", "Pending"));

        // Notify adapter about data change
        taskAdapter.notifyDataSetChanged();
    }

    // Task Adapter for RecyclerView
    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private List<DashboardTask> taskList;

        public TaskAdapter(List<DashboardTask> taskList) {
            this.taskList = taskList;
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Inflate the item layout (task item)
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dashboard_task_item, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            DashboardTask dashboardTask = taskList.get(position);
            holder.taskTitle.setText(dashboardTask.getTitle());
            holder.taskStatus.setText(dashboardTask.getStatus());

            // Set task icon
            holder.taskIcon.setImageResource(R.drawable.ic_task);

            // Initially set task details to be hidden
            holder.taskDetailsContainer.setVisibility(View.GONE);
            holder.toggleArrow.setImageResource(R.drawable.ic_arrow_down); // Set the initial arrow as "down"

            // Set click listener for toggling task details
            holder.toggleArrow.setOnClickListener(v -> toggleTaskDetails(holder));

            // Set click listener for the arrowRight to go to task details
            holder.arrowRight.setOnClickListener(v -> {
                // Open the task details page
                Intent intent = new Intent(getContext(), TaskDetailsActivity.class);
                intent.putExtra("taskId", dashboardTask.getId());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        private void toggleTaskDetails(TaskViewHolder holder) {
            if (holder.taskDetailsContainer.getVisibility() == View.GONE) {
                holder.taskDetailsContainer.setVisibility(View.VISIBLE);
                holder.toggleArrow.setImageResource(R.drawable.ic_arrow_up);
            } else {
                holder.taskDetailsContainer.setVisibility(View.GONE);
                holder.toggleArrow.setImageResource(R.drawable.ic_arrow_down);
            }
        }

        // ViewHolder class for task items
        public class TaskViewHolder extends RecyclerView.ViewHolder {

            TextView taskTitle, taskStatus;
            ImageView taskIcon, toggleArrow, arrowRight;
            LinearLayout taskDetailsContainer;

            public TaskViewHolder(View itemView) {
                super(itemView);
                taskTitle = itemView.findViewById(R.id.taskTitle);
                taskStatus = itemView.findViewById(R.id.taskStatus);
                taskIcon = itemView.findViewById(R.id.taskIcon);
                taskDetailsContainer = itemView.findViewById(R.id.taskDetailsContainer);
                toggleArrow = itemView.findViewById(R.id.toggleArrow); // The arrow icon
                arrowRight = itemView.findViewById(R.id.arrowRight); // New arrow icon for task details page
            }
        }
    }

    // Dashboard Task model class
    public static class DashboardTask {
        private String id;
        private String title;
        private String status;

        public DashboardTask(String id, String title, String status) {
            this.id = id;
            this.title = title;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getStatus() {
            return status;
        }
    }
}
