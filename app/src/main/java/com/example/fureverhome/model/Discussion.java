package com.example.fureverhome.model;

import java.io.Serializable;

public class Discussion implements Serializable {
    private int imageId;
    private String id;
    private String postDate;
    private String title;
    private String description;
    private String type;

    // Updated constructor to include id
    public Discussion(int imageId, String title, String date, String id, String description, String type) {
        this.id = id;
        this.title = title;
        this.postDate = date;
        this.imageId = imageId;
        this.description = description;
        this.type = type;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPostDate() {
            return postDate;
    }
    public int getImageId() {
        return imageId;
    }


    public String getDescription() {
        return description;
    }

    public String getType() {return type;}
}
