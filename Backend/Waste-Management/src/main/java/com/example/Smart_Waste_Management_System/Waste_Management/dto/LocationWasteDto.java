package com.example.Smart_Waste_Management_System.Waste_Management.dto;



public class LocationWasteDto {
    private String location;
    private int totalWaste;
    private int binCount;
    private double averageWaste;
    
    // Constructors
    public LocationWasteDto() {}
    
    public LocationWasteDto(String location, int totalWaste, int binCount) {
        this.location = location;
        this.totalWaste = totalWaste;
        this.binCount = binCount;
        this.averageWaste = binCount > 0 ? (double) totalWaste / binCount : 0;
    }
    
    // Getters and Setters
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public int getTotalWaste() { return totalWaste; }
    public void setTotalWaste(int totalWaste) { this.totalWaste = totalWaste; }
    
    public int getBinCount() { return binCount; }
    public void setBinCount(int binCount) { this.binCount = binCount; }
    
    public double getAverageWaste() { return averageWaste; }
    public void setAverageWaste(double averageWaste) { this.averageWaste = averageWaste; }
}