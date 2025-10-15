package com.example.Smart_Waste_Management_System.Waste_Management.dto;

public class WasteTypeDto {
    private String wasteType;
    private int totalWaste;
    private int binCount;
    private double percentage;

    // Constructors
    public WasteTypeDto() {
    }

    public WasteTypeDto(String wasteType, int totalWaste, int binCount, int totalWasteOverall) {
        this.wasteType = wasteType;
        this.totalWaste = totalWaste;
        this.binCount = binCount;
        this.percentage = totalWasteOverall > 0 ? (double) totalWaste / totalWasteOverall * 100 : 0;
    }

    // Getters and Setters
    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public int getTotalWaste() {
        return totalWaste;
    }

    public void setTotalWaste(int totalWaste) {
        this.totalWaste = totalWaste;
    }

    public int getBinCount() {
        return binCount;
    }

    public void setBinCount(int binCount) {
        this.binCount = binCount;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}