package com.example.fureverhome.model;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskUtils {

    public static List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();

        taskList.add(new Task(
                "1", "Foster a Kitten", "Downtown Shelter", "2024/03/10 2 PM",
                R.drawable.ic_pet, "Foster Care", "2024/03/01", "2024/03/10", "2024/04/01",
                "3 Weeks", "Happy Paws Shelter",
                "Kitten has received vaccines.\nNeeds feeding twice a day.\nClean litter box daily.\nAdoptable after 3 weeks."
        ));

        taskList.add(new Task(
                "2", "Help at Adoption Event", "BC SPCA", "2024/04/10 10 AM",
                R.drawable.ic_pet_supplies, "Event Assistance", "2024/03/28", "2024/04/10", "2024/04/10",
                "1 Day", "BC SPCA",
                "Assist with setting up tables, welcoming guests.\nAnswer basic questions about animals.\nGreat social opportunity!"
        ));

        taskList.add(new Task(
                "3", "Transport a Dog", "Rutland → Foster Home", "2024/04/15 4 PM",
                R.drawable.ic_dog, "Animal Transport", "2024/04/01", "2024/04/15", "2024/04/15",
                "Same Day", "Kelowna Rescue Group",
                "Pick up from shelter in Rutland.\nDeliver to foster family 15km away.\nDog is anxious, needs gentle handling."
        ));

        return taskList;
    }

    public static Task getTaskById(List<Task> taskList, String taskId) {
        for (Task task : taskList) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }
}
