package com.example.fureverhome.model;

import java.io.Serializable;

public class Task implements Serializable {
    private String id;
    private String title;
    private String location;
    private String time;
    private int imageResId;
    private String taskType;
    private String postedDate;
    private String startDate;
    private String endDate;
    private String duration;
    private String organizer;
    private String description;

    // Updated constructor to include id
    public Task(String id, String title, String location, String time, int imageResId, String taskType,
                String postedDate, String startDate, String endDate, String duration,
                String organizer, String description) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.time = time;
        this.imageResId = imageResId;
        this.taskType = taskType;
        this.postedDate = postedDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.organizer = organizer;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDuration() {
        return duration;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getDescription() {
        return description;
    }
}
