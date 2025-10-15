//packages
import { BrowserRouter, Routes, Route } from 'react-router-dom';


//files
import User from "./Users/User"
import Admin from "./Users/Admin"
import Bro from "./Users/Bro"
import Landing from './Common/Landing';
import Header from './Common/Header';
import Footer from './Common/Footer';
import Login from './Auth/Login';
import Register from './Auth/Register';
import Contact from './Common/Contact';
import About from './Common/About';

import Dashboard from './Waster-managemnt-authority/Dashboard';
import Reports from './Waster-managemnt-authority/Reports';
import Settings from './Waster-managemnt-authority/Settings';
import Analytics from './Waster-managemnt-authority/Analytics';

import Shell from './Waster-managemnt-authority/Shell';

import AddSmartBin from './AddSmartBin';

export default function App() {

  return (
    <BrowserRouter>
      <Header />
      <div className='min-h-dvh'>
        <Routes>
          <Route path='/addBin' element={<AddSmartBin/>}/>
          <Route path='/' element={<Landing />} />

          <Route path='/user' element={<User />} />
          <Route path='/admin' element={<Admin />} />
          <Route path='/bro' element={<Bro />} />


          <Route path='/login' element={<Login />} />
          <Route path='/register' element={<Register />} />
          <Route path='/contact' element={<Contact />} />
          <Route path='/about' element={<About />} />
        </Routes>

        <Routes>
          <Route element={<Shell />}>
            <Route index element={<Dashboard />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/reports" element={<Reports />} />
            <Route path="/analytics" element={<Analytics />} />
            <Route path="/settings" element={<Settings />} />
          </Route>
        </Routes>
      </div>
      <Footer />
    </BrowserRouter>
  )

}