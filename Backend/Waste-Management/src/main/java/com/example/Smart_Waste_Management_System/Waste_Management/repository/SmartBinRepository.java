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

    List<SmartBin> findByWasteType(String wasteType);

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

    // NEW: Aggregations for waste type analytics
    @Aggregation(pipeline = {
            "{ $group: { _id: '$wasteType', totalWaste: { $sum: '$currentLevel' }, binCount: { $sum: 1 } } }"
    })
    List<WasteTypeSummary> findWasteByType();

    // NEW: Find total e-waste
    @Aggregation(pipeline = {
            "{ $match: { wasteType: 'e-waste' } }",
            "{ $group: { _id: null, totalEWaste: { $sum: '$currentLevel' } } }"
    })
    EWasteStats findTotalEWaste();

    // NEW: Find total recyclable waste
    @Aggregation(pipeline = {
            "{ $match: { wasteType: 'recyclable' } }",
            "{ $group: { _id: null, totalRecyclable: { $sum: '$currentLevel' } } }"
    })
    RecyclableStats findTotalRecyclableWaste();

    // NEW: Find location with most waste (already partially covered by
    // findWasteByLocation, but let's make it explicit)
    @Aggregation(pipeline = {
            "{ $group: { _id: '$location', totalWaste: { $sum: '$currentLevel' } } }",
            "{ $sort: { totalWaste: -1 } }",
            "{ $limit: 1 }"
    })
    List<LocationSummary> findLocationWithMostWaste();

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

    // NEW: Interface for waste type summary
    interface WasteTypeSummary {
        String get_id(); // waste type

        int getTotalWaste();

        int getBinCount();
    }

    // NEW: Interface for e-waste stats
    interface EWasteStats {
        int getTotalEWaste();
    }

    // NEW: Interface for recyclable waste stats
    interface RecyclableStats {
        int getTotalRecyclable();
    }
}