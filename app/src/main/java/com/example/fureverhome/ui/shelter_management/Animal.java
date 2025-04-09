package com.example.fureverhome.ui.shelter_management;

import java.io.Serializable;
import java.util.List;

public class Animal implements Serializable {
    private String name;
    private String species;
    private String breed;
    private String colour;
    private String size;
    private int age;
    private String gender;
    private List<Integer> images;
    private String location;
    private String status;
    private String description;
    private String id;

    public Animal(String name, String species, String breed, String colour, String size, int age,
                  String gender, List<Integer> images, String location, String status, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.colour = colour;
        this.size = size;
        this.age = age;
        this.gender = gender;
        this.images = images;
        this.location = location;
        this.status = status;
        this.description = description;
        this.id = "";
    }

    // --- Getters and Setters ---

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getBreed() { return breed; }
    public String getColour() { return colour; }
    public String getSize() { return size; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public List<Integer> getImages() { return images; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }

    public void setName(String name) { this.name = name; }
    public void setSpecies(String species) { this.species = species; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setColour(String colour) { this.colour = colour; }
    public void setSize(String size) { this.size = size; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setImages(List<Integer> images) { this.images = images; }
    public void setLocation(String location) { this.location = location; }
    public void setStatus(String status) { this.status = status; }
    public void setDescription(String description) { this.description = description; }

    public List<Integer> getImageList() { return images; }

    public void setImageList(List<Integer> imageList) {
        this.images = imageList;
    }
}
