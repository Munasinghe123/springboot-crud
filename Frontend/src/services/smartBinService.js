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
};

export default smartBinService;