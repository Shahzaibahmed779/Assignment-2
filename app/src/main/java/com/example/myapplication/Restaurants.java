package com.example.myapplication;

public class Restaurants implements Comparable<Restaurants> {
    private double rating; // Changed from String to double
    private String name;
    private String location;
    private String phoneNumber;
    private String description;

    // Constructor
    public Restaurants(String rating, String name, String location, String phoneNumber, String description) {
        this.rating = Double.parseDouble(rating);;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    // Getters
    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public int compareTo(Restaurants another) {
        return Double.compare(this.rating, another.rating);
    }
}