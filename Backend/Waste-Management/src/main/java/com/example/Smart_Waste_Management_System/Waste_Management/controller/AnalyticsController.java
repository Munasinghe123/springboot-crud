package com.example.Smart_Waste_Management_System.Waste_Management.controller;

import com.example.Smart_Waste_Management_System.Waste_Management.dto.AnalyticsDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.BinStatusDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.DashboardSummaryDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.LocationWasteDto;
import com.example.Smart_Waste_Management_System.Waste_Management.dto.TotalWasteDto;
import com.example.Smart_Waste_Management_System.Waste_Management.service.AnalyticsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardSummaryDto> getDashboard() {
        return ResponseEntity.ok(analyticsService.getDashboardAnalytics());
    }

    @GetMapping("/locations/waste")
    public ResponseEntity<List<LocationWasteDto>> getWasteByLocation() {
        return ResponseEntity.ok(analyticsService.getWasteByLocation());
    }

    @GetMapping("/bins/status")
    public ResponseEntity<List<BinStatusDto>> getBinStatus() {
        return ResponseEntity.ok(analyticsService.getBinStatusSummary());
    }

    @GetMapping("/waste/total")
    public ResponseEntity<TotalWasteDto> getTotalWaste() {
        return ResponseEntity.ok(analyticsService.getTotalWasteMetrics());
    }

    // Chart-specific endpoints (for React charts)
    @GetMapping("/charts/locations")
    public ResponseEntity<List<AnalyticsDto>> getLocationChartData() {
        return ResponseEntity.ok(analyticsService.getWasteByLocationForCharts());
    }

    @GetMapping("/charts/status")
    public ResponseEntity<List<AnalyticsDto>> getStatusChartData() {
        return ResponseEntity.ok(analyticsService.getBinStatusForCharts());
    }
}