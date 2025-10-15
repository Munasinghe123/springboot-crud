package com.example.Smart_Waste_Management_System.Waste_Management.dto;



public class TotalWasteDto {
    private int totalWaste;
    private int totalCapacity;
    private double utilizationRate;
    
    // Constructors
    public TotalWasteDto() {}
    
    public TotalWasteDto(int totalWaste, int totalCapacity) {
        this.totalWaste = totalWaste;
        this.totalCapacity = totalCapacity;
        this.utilizationRate = totalCapacity > 0 ? (double) totalWaste / totalCapacity * 100 : 0;
    }
    
    // Getters and Setters
    public int getTotalWaste() { return totalWaste; }
    public void setTotalWaste(int totalWaste) { this.totalWaste = totalWaste; }
    
    public int getTotalCapacity() { return totalCapacity; }
    public void setTotalCapacity(int totalCapacity) { this.totalCapacity = totalCapacity; }
    
    public double getUtilizationRate() { return utilizationRate; }
    public void setUtilizationRate(double utilizationRate) { this.utilizationRate = utilizationRate; }
}
