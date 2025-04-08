package com.example.fureverhome.model;

import java.io.Serializable;

public class Event implements Serializable {
    private int imageId;
    private String id;
    private String startDate;
    private String time;
    private String duration;
    private String title;
    private String location;
    private String organizer;
    private String taskType;
    private String description;
    private String postedDate;

    // Updated constructor to include id
    public Event(int imageId, String id, String startDate, String time, String duration, String title, String location, String organizer, String taskType,
                 String description, String postedDate) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.time = time;
        this.imageId = imageId;
        this.taskType = taskType;
        this.postedDate = postedDate;
        this.startDate = startDate;
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

    public int getImageId() {
        return imageId;
    }

    public String getEventType() {
        return taskType;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public String getStartDate() {
        return startDate;
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
