package com.example.fureverhome.model;

import com.example.fureverhome.R;

import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    private static final List<Event> eventList1 = new ArrayList<>();

    //For Event Section
    public static List<Event> loadEvents() {
        List<Event> eventList = new ArrayList<>();

        //"Volunteer", "Workshop", "Networking", "Seminar", "Others"
        eventList.add(new Event(
                R.drawable.ic_pet, "1", "2025/04/19", "12:00", "2hrs", "Adoption Event", "Okanagan",
                        " Me", "Seminar", "Come", "2025/04/18"));

        List<Event> mergedList = new ArrayList<>();
        mergedList.addAll(eventList);
        mergedList.addAll(eventList1);
        return mergedList;
    }

    public static void addEvent(Event event) {
        eventList1.add(event);
    }

    public static Event getEventById(List<Event> eventList, String eventId) {
        for (Event task : eventList) {
            if (task.getId().equals(eventId)) {
                return task;
            }
        }
        return null;
    }
}
