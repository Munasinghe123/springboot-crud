import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const smartBinService = {
  createSmartBin: (binData) => api.post('/bins', binData),
  getAllBins: () => api.get('/bins'),
  getBinById: (id) => api.get(`/bins/${id}`),
  updateBin: (id, binData) => api.put(`/bins/${id}`, binData),
  deleteBin: (id) => api.delete(`/bins/${id}`),
  
  // Analytics endpoints
  getDashboardData: () => api.get('/analytics/dashboard'),
  getWasteByLocation: () => api.get('/analytics/locations/waste'),
  getBinStatus: () => api.get('/analytics/bins/status'),
  getTotalWaste: () => api.get('/analytics/waste/total'),
  getWasteByType: () => api.get('/analytics/waste/type'),
  getTotalEWaste: () => api.get('/analytics/waste/e-waste'),
  getTotalRecyclableWaste: () => api.get('/analytics/waste/recyclable'),
  getLocationWithMostWaste: () => api.get('/analytics/locations/most-waste'),
  
  // Chart data endpoints
  getLocationChartData: () => api.get('/analytics/charts/locations'),
  getStatusChartData: () => api.get('/analytics/charts/status'),
  getWasteTypeChartData: () => api.get('/analytics/charts/waste-type'),
};

export default smartBinService;