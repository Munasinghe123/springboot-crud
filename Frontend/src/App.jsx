//packages
import { BrowserRouter, Routes, Route } from 'react-router-dom';


//files
import User from "./Users/User"
import Admin from "./Users/Admin"
import Bro from "./Users/Bro"
import Landing from './Landing';
import Header from './Common/Header';
import Footer from './Common/Footer';
import Login from './Auth/Login';
import Register from './Auth/Register';
import Contact from './Common/Contact';
import About from './Common/About';

export default function App() {

  return (
    <BrowserRouter>
      <Header />
      <div className='min-h-dvh'>
        <Routes>
          <Route path='/' element={<Landing />} />

          <Route path='/user' element={<User />} />
          <Route path='/admin' element={<Admin />} />
          <Route path='/bro' element={<Bro />} />

          <Route path='/login' element={<Login />} />
          <Route path='/register' element={<Register />} />
          <Route path='/contact' element={<Contact />} />
          <Route path='/about' element={<About />} />
        </Routes>
      </div>
      <Footer />
    </BrowserRouter>
  )

}