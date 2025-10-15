package com.example.Smart_Waste_Management_System.Waste_Management.service;

import com.example.Smart_Waste_Management_System.Waste_Management.dto.AnalyticsDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.BinStatusDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.DashboardSummaryDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.LocationWasteDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.TotalWasteDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.WasteTypeDto;
import com.example.Smart_Waste_Management_System.Waste_Management.repository.SmartBinRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final SmartBinRepository smartBinRepository;

    public AnalyticsService(SmartBinRepository smartBinRepository) {
        this.smartBinRepository = smartBinRepository;
    }

    public DashboardSummaryDto getDashboardAnalytics() {
        DashboardSummaryDto dashboard = new DashboardSummaryDto();

        // Total waste metrics
        var totalStats = smartBinRepository.findTotalWasteStats();
        dashboard.setTotalWaste(new TotalWasteDto(
                totalStats.getTotalWaste(),
                totalStats.getTotalCapacity()));

        // Waste by location
        List<LocationWasteDto> locationWaste = smartBinRepository.findWasteByLocation()
                .stream()
                .map(summary -> new LocationWasteDto(
                        summary.get_id(),
                        summary.getTotalWaste(),
                        summary.getBinCount()))
                .collect(Collectors.toList());
        dashboard.setWasteByLocation(locationWaste);

        // Bin status summary
        List<SmartBinRepository.StatusSummary> statusSummaries = smartBinRepository.findBinStatusSummary();
        int totalBins = statusSummaries.stream().mapToInt(SmartBinRepository.StatusSummary::getCount).sum();

        List<BinStatusDto> binStatus = statusSummaries.stream()
                .map(summary -> new BinStatusDto(
                        summary.get_id(),
                        summary.getCount(),
                        totalBins))
                .collect(Collectors.toList());
        dashboard.setBinStatus(binStatus);

        // Top 5 locations with most waste
        List<LocationWasteDto> topLocations = locationWaste.stream()
                .limit(5)
                .collect(Collectors.toList());
        dashboard.setTopLocations(topLocations);

        return dashboard;
    }

    public List<LocationWasteDto> getWasteByLocation() {
        return smartBinRepository.findWasteByLocation()
                .stream()
                .map(summary -> new LocationWasteDto(
                        summary.get_id(),
                        summary.getTotalWaste(),
                        summary.getBinCount()))
                .collect(Collectors.toList());
    }

    public List<BinStatusDto> getBinStatusSummary() {
        List<SmartBinRepository.StatusSummary> statusSummaries = smartBinRepository.findBinStatusSummary();
        int totalBins = statusSummaries.stream().mapToInt(SmartBinRepository.StatusSummary::getCount).sum();

        return statusSummaries.stream()
                .map(summary -> new BinStatusDto(
                        summary.get_id(),
                        summary.getCount(),
                        totalBins))
                .collect(Collectors.toList());
    }

    public TotalWasteDto getTotalWasteMetrics() {
        var stats = smartBinRepository.findTotalWasteStats();
        return new TotalWasteDto(stats.getTotalWaste(), stats.getTotalCapacity());
    }

    // NEW: Get waste by type
    public List<WasteTypeDto> getWasteByType() {
        List<SmartBinRepository.WasteTypeSummary> wasteTypeSummaries = smartBinRepository.findWasteByType();
        int totalWasteOverall = wasteTypeSummaries.stream().mapToInt(SmartBinRepository.WasteTypeSummary::getTotalWaste)
                .sum();

        return wasteTypeSummaries.stream()
                .map(summary -> new WasteTypeDto(
                        summary.get_id(),
                        summary.getTotalWaste(),
                        summary.getBinCount(),
                        totalWasteOverall))
                .collect(Collectors.toList());
    }

    // NEW: Get total e-waste
    public int getTotalEWaste() {
        var eWasteStats = smartBinRepository.findTotalEWaste();
        return eWasteStats != null ? eWasteStats.getTotalEWaste() : 0;
    }

    // NEW: Get total recyclable waste
    public int getTotalRecyclableWaste() {
        var recyclableStats = smartBinRepository.findTotalRecyclableWaste();
        return recyclableStats != null ? recyclableStats.getTotalRecyclable() : 0;
    }

    // NEW: Get location with most waste
    public LocationWasteDto getLocationWithMostWaste() {
        List<SmartBinRepository.LocationSummary> locationSummaries = smartBinRepository.findLocationWithMostWaste();
        if (!locationSummaries.isEmpty()) {
            SmartBinRepository.LocationSummary summary = locationSummaries.get(0);
            return new LocationWasteDto(
                    summary.get_id(),
                    summary.getTotalWaste(),
                    summary.getBinCount());
        }
        return new LocationWasteDto("No data", 0, 0);
    }

    // For chart data (frontend-friendly format)
    public List<AnalyticsDto> getWasteByLocationForCharts() {
        return smartBinRepository.findWasteByLocation()
                .stream()
                .map(summary -> new AnalyticsDto(
                        summary.get_id(),
                        summary.getTotalWaste(),
                        "location"))
                .collect(Collectors.toList());
    }

    public List<AnalyticsDto> getBinStatusForCharts() {
        List<SmartBinRepository.StatusSummary> statusSummaries = smartBinRepository.findBinStatusSummary();
        int totalBins = statusSummaries.stream().mapToInt(SmartBinRepository.StatusSummary::getCount).sum();

        return statusSummaries.stream()
                .map(summary -> new AnalyticsDto(
                        summary.get_id(),
                        summary.getCount(),
                        "status"))
                .collect(Collectors.toList());
    }

    // NEW: For waste type chart data
    public List<AnalyticsDto> getWasteByTypeForCharts() {
        return smartBinRepository.findWasteByType()
                .stream()
                .map(summary -> new AnalyticsDto(
                        summary.get_id(),
                        summary.getTotalWaste(),
                        "wasteType"))
                .collect(Collectors.toList());
    }
}