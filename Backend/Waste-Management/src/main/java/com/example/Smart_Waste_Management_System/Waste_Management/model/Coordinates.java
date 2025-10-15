package com.example.Smart_Waste_Management_System.Waste_Management.model;

import lombok.Data;

@Data
public class Coordinates {
    private double latitude;
    private double longitude;

    // Default constructor
    public Coordinates() {
    }

    // Constructor with parameters
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}