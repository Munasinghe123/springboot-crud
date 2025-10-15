import React, { useState, useEffect } from 'react';
import { smartBinService } from '../services/smartBinService';

function Reports() {
  const [reportData, setReportData] = useState({
    wasteByLocation: [],
    wasteByType: [],
    binStatus: [],
    eWasteTotal: 0,
    recyclableTotal: 0,
    locationWithMostWaste: null
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      setLoading(true);
      
      // Fetch all report data in parallel
      const [
        wasteByLocationResponse,
        wasteByTypeResponse,
        binStatusResponse,
        eWasteResponse,
        recyclableResponse,
        locationResponse
      ] = await Promise.all([
        smartBinService.getWasteByLocation(),
        smartBinService.getWasteByType(),
        smartBinService.getBinStatus(),
        smartBinService.getTotalEWaste(),
        smartBinService.getTotalRecyclableWaste(),
        smartBinService.getLocationWithMostWaste()
      ]);

      setReportData({
        wasteByLocation: wasteByLocationResponse.data,
        wasteByType: wasteByTypeResponse.data,
        binStatus: binStatusResponse.data,
        eWasteTotal: eWasteResponse.data,
        recyclableTotal: recyclableResponse.data,
        locationWithMostWaste: locationResponse.data
      });
    } catch (err) {
      setError('Failed to fetch report data');
      console.error('Error fetching report data:', err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
        <strong className="font-bold">Error! </strong>
        <span className="block sm:inline">{error}</span>
      </div>
    );
  }

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">Detailed Reports</h1>
      
      {/* Waste Type Summary */}
      <div className="bg-white rounded-lg shadow p-6 mb-8">
        <h2 className="text-xl font-bold text-gray-800 mb-4">Waste by Type</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
          <div className="bg-blue-50 p-4 rounded-lg">
            <h3 className="font-medium text-blue-800">E-Waste</h3>
            <p className="text-2xl font-bold text-blue-600">{reportData.eWasteTotal} kg</p>
          </div>
          <div className="bg-green-50 p-4 rounded-lg">
            <h3 className="font-medium text-green-800">Recyclable</h3>
            <p className="text-2xl font-bold text-green-600">{reportData.recyclableTotal} kg</p>
          </div>
          <div className="bg-yellow-50 p-4 rounded-lg">
            <h3 className="font-medium text-yellow-800">Total Waste</h3>
            <p className="text-2xl font-bold text-yellow-600">
              {reportData.wasteByType.reduce((sum, type) => sum + type.totalWaste, 0)} kg
            </p>
          </div>
          <div className="bg-purple-50 p-4 rounded-lg">
            <h3 className="font-medium text-purple-800">Locations</h3>
            <p className="text-2xl font-bold text-purple-600">{reportData.wasteByLocation.length}</p>
          </div>
        </div>
        
        <div className="overflow-x-auto">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Waste Type</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Total (kg)</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Bins</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Percentage</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {reportData.wasteByType.map((wasteType, index) => (
                <tr key={index}>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 capitalize">{wasteType.wasteType}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{wasteType.totalWaste}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{wasteType.binCount}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{wasteType.percentage.toFixed(1)}%</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      
      {/* Location Analysis */}
      <div className="bg-white rounded-lg shadow p-6 mb-8">
        <h2 className="text-xl font-bold text-gray-800 mb-4">Location Analysis</h2>
        
        {reportData.locationWithMostWaste && reportData.locationWithMostWaste.location !== "No data" && (
          <div className="mb-6 p-4 bg-blue-50 rounded-lg">
            <h3 className="font-medium text-blue-800">Highest Waste Location</h3>
            <p className="text-lg font-bold text-blue-600">
              {reportData.locationWithMostWaste.location} with {reportData.locationWithMostWaste.totalWaste} kg across {reportData.locationWithMostWaste.binCount} bins
            </p>
          </div>
        )}
        
        <div className="overflow-x-auto">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Location</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Total Waste (kg)</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Number of Bins</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Average per Bin (kg)</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {reportData.wasteByLocation.map((location, index) => (
                <tr key={index}>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{location.location}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{location.totalWaste}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{location.binCount}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{location.averageWaste.toFixed(1)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      
      {/* Bin Status Report */}
      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-xl font-bold text-gray-800 mb-4">Bin Status Report</h2>
        <div className="overflow-x-auto">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Number of Bins</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Percentage</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {reportData.binStatus.map((status, index) => (
                <tr key={index}>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{status.status}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{status.count}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{status.percentage.toFixed(1)}%</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default Reports;