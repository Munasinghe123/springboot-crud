package com.example.Smart_Waste_Management_System.Waste_Management.service;

import com.example.Smart_Waste_Management_System.Waste_Management.model.SmartBin;
import com.example.Smart_Waste_Management_System.Waste_Management.repository.SmartBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SmartBinService {

    @Autowired
    private SmartBinRepository smartBinRepository;

    public SmartBin createSmartBin(SmartBin smartBin) {
        LocalDateTime now = LocalDateTime.now();
        smartBin.setCreatedAt(now);
        smartBin.setLastUpdated(now);
        return smartBinRepository.save(smartBin);
    }

    public List<SmartBin> getAllSmartBins() {
        return smartBinRepository.findAll();
    }

    public Optional<SmartBin> getSmartBinById(String id) {
        return smartBinRepository.findById(id);
    }

    public SmartBin updateSmartBin(String id, SmartBin smartBinDetails) {
        Optional<SmartBin> optionalBin = smartBinRepository.findById(id);
        if (optionalBin.isPresent()) {
            SmartBin bin = optionalBin.get();

            // Update fields
            bin.setLocation(smartBinDetails.getLocation());
            bin.setCoordinates(smartBinDetails.getCoordinates());
            bin.setCurrentLevel(smartBinDetails.getCurrentLevel());
            bin.setCapacity(smartBinDetails.getCapacity());
            bin.setStatus(smartBinDetails.getStatus());
            bin.setLastUpdated(LocalDateTime.now());

            return smartBinRepository.save(bin);
        }
        throw new RuntimeException("Smart bin not found with id: " + id);
    }

    public void deleteSmartBin(String id) {
        smartBinRepository.deleteById(id);
    }
}