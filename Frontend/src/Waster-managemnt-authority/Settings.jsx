import React from 'react';

function Settings() {
  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">Settings</h1>
      
      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-xl font-bold text-gray-800 mb-4">System Configuration</h2>
        
        <div className="space-y-6">
          <div>
            <h3 className="text-lg font-medium text-gray-700 mb-2">Notification Settings</h3>
            <div className="space-y-2">
              <div className="flex items-center">
                <input type="checkbox" id="email-notifications" className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded" />
                <label htmlFor="email-notifications" className="ml-2 block text-sm text-gray-900">
                  Email notifications for bin status updates
                </label>
              </div>
              <div className="flex items-center">
                <input type="checkbox" id="sms-notifications" className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded" />
                <label htmlFor="sms-notifications" className="ml-2 block text-sm text-gray-900">
                  SMS notifications for critical alerts
                </label>
              </div>
            </div>
          </div>
          
          <div>
            <h3 className="text-lg font-medium text-gray-700 mb-2">Data Management</h3>
            <div className="space-y-2">
              <div className="flex items-center">
                <input type="checkbox" id="auto-archive" className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded" />
                <label htmlFor="auto-archive" className="ml-2 block text-sm text-gray-900">
                  Automatically archive data older than 1 year
                </label>
              </div>
            </div>
          </div>
          
          <div>
            <h3 className="text-lg font-medium text-gray-700 mb-2">Report Settings</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label htmlFor="report-frequency" className="block text-sm font-medium text-gray-700">Report Frequency</label>
                <select id="report-frequency" className="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm rounded-md">
                  <option>Daily</option>
                  <option>Weekly</option>
                  <option>Monthly</option>
                </select>
              </div>
              <div>
                <label htmlFor="report-format" className="block text-sm font-medium text-gray-700">Report Format</label>
                <select id="report-format" className="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm rounded-md">
                  <option>PDF</option>
                  <option>Excel</option>
                  <option>CSV</option>
                </select>
              </div>
            </div>
          </div>
          
          <div className="pt-4">
            <button type="button" className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
              Save Settings
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Settings;