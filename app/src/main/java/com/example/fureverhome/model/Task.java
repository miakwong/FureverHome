package com.example.fureverhome.model;

public class Task {
    public String title;
    public String location;
    public String time;
    public int imageResId;
    public String taskType;

    public Task(String title, String location, String time, int imageResId, String taskType) {
        this.title = title;
        this.location = location;
        this.time = time;
        this.imageResId = imageResId;
        this.taskType = taskType;
    }
}
