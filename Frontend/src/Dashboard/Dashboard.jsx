import React from 'react'
import { Menu, X } from 'lucide-react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

function Dashboard() {

    const [isOpen, setIsOpen] = useState(false);

    const toggleOpen = () => {
        setIsOpen(!isOpen)
    }

    return (
        <section className='h-screen flex'>

            {/* side bar */}
            <div className={`relative bg-green-500 h-screen overflow-hidden space-y-6 pt-4
                    transition-[width] duration-300 ease-out
                    ${isOpen ? 'w-[340px]' : 'w-16'}`}>

                <button onClick={toggleOpen}>
                    {isOpen ?
                        (<div className='flex flex-col gap-4'>

                            <div className='flex flex-row'>
                                <p className=''>City Waste Management Authority Dashboard</p>
                                <div> <X /></div>
                            </div>

                        </div>) :
                        <div className='absolute right-1 top-4'>
                            <Menu />
                        </div>
                    }
                </button>

                {isOpen && (
                    <div className='space-y-4 flex flex-col bg-amber-600'>
                        <Link className='text-black'>Reports</Link>
                        <Link>Reports</Link>
                        <Link>Reports</Link>
                    </div>

                )}

            </div>

            {/* page content */}

            <div className='bg-red-400 h-screen w-full relative'>

                {isOpen &&
                    <div
                        className=" absolute inset-0 bg-black/50 backdrop-blur-[1px] z-10"
                        onClick={toggleOpen}
                        aria-hidden="true"
                    >
                    </div>}
            </div>


        </section>

    )
}

export default Dashboard
