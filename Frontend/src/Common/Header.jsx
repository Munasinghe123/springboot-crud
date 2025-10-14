import React from 'react'
import logo from '../assets/seethrough.png';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <div className='justify-between flex items-center p-3 bg-blue-300'>

      {/* logo */}
      <div>
        <Link to='/'>
          <img src={logo} alt='header logo' className='h-20 w-24 rounded-3xl' />
        </Link>

      </div>

      {/* common links */}
      <div className='space-x-5'>
        <Link to='/contact'> Contact</Link>
        <Link to="/about"> About</Link>

      </div>

      {/* buttons */}
      <div className='space-x-5'>
        <Link to="/login" className=' rounded-4xl p-2 bg-blue-600 text-white hover:cursor-pointer hover:bg-blue-700'>Login </Link>
        <Link to="/register" className=' rounded-4xl p-2 bg-blue-600 text-white hover:cursor-pointer hover:bg-blue-700'> Register</Link>
      </div>

    </div>
  )
}

export default Header
