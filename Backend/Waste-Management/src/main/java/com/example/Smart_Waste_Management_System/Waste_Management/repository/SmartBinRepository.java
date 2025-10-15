package com.example.Smart_Waste_Management_System.Waste_Management.repository;



import com.example.Smart_Waste_Management_System.Waste_Management.model.SmartBin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartBinRepository extends MongoRepository<SmartBin, String> {
    
    // Basic queries
    List<SmartBin> findByLocation(String location);
    List<SmartBin> findByStatus(String status);
    
    // Aggregation queries for analytics
    @Aggregation(pipeline = {
        "{ $group: { _id: '$location', totalWaste: { $sum: '$currentLevel' }, binCount: { $sum: 1 } } }",
        "{ $sort: { totalWaste: -1 } }"
    })
    List<LocationSummary> findWasteByLocation();
    
    @Aggregation(pipeline = {
        "{ $group: { _id: '$status', count: { $sum: 1 } } }"
    })
    List<StatusSummary> findBinStatusSummary();
    
    @Aggregation(pipeline = {
        "{ $group: { _id: null, totalWaste: { $sum: '$currentLevel' }, totalCapacity: { $sum: '$capacity' } } }"
    })
    TotalWasteStats findTotalWasteStats();
    
    // Interface projections for aggregation results
    interface LocationSummary {
        String get_id(); // location name
        int getTotalWaste();
        int getBinCount();
    }
    
    interface StatusSummary {
        String get_id(); // status
        int getCount();
    }
    
    interface TotalWasteStats {
        int getTotalWaste();
        int getTotalCapacity();
    }
}