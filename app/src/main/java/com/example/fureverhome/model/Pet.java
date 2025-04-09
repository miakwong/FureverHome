package com.example.fureverhome.model;

public class Pet {
    private String id;
    private String name;
    private int imageResId;
    private int[] galleryResIds;

    public Pet(String id, String name, int imageResId, int[] galleryResIds) {
        this.id = id;
        this.name = name;
        this.imageResId = imageResId;
        this.galleryResIds = galleryResIds;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public int[] getGalleryResIds() { return galleryResIds; }
}

