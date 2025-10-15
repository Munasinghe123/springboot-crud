package com.example.Smart_Waste_Management_System.Waste_Management.controller;

import com.example.Smart_Waste_Management_System.Waste_Management.model.SmartBin;
import com.example.Smart_Waste_Management_System.Waste_Management.service.SmartBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class SmartBinController {

    @Autowired
    private SmartBinService smartBinService;

    // Create a new smart bin
    @PostMapping
    public ResponseEntity<?> createSmartBin(@RequestBody SmartBin smartBin) {
        try {
            SmartBin savedBin = smartBinService.createSmartBin(smartBin);
            return new ResponseEntity<>(savedBin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating smart bin: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all smart bins
    @GetMapping
    public ResponseEntity<List<SmartBin>> getAllSmartBins() {
        try {
            List<SmartBin> bins = smartBinService.getAllSmartBins();
            return new ResponseEntity<>(bins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a specific smart bin by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSmartBinById(@PathVariable String id) {
        try {
            Optional<SmartBin> bin = smartBinService.getSmartBinById(id);
            if (bin.isPresent()) {
                return new ResponseEntity<>(bin.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Smart bin not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving smart bin: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a smart bin
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSmartBin(@PathVariable String id, @RequestBody SmartBin smartBinDetails) {
        try {
            SmartBin updatedBin = smartBinService.updateSmartBin(id, smartBinDetails);
            return new ResponseEntity<>(updatedBin, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating smart bin: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a smart bin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSmartBin(@PathVariable String id) {
        try {
            smartBinService.deleteSmartBin(id);
            return new ResponseEntity<>("Smart bin deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting smart bin: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}