import React from 'react'
import image from '../assets/heroImage3.jpg'
import { useState } from 'react'
import axios from 'axios';
import {useNavigate} from 'react-router-dom';

function Register() {

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState("");

    const [showPw, setShowPw] = useState(false);

    const togglePw = (e) => {
        setShowPw(!showPw);
    }


    const register = async () => {
        try {

            if (password.length < 6) {

                setError("Password must be at least 6 characters long");
                return
            }

            const response = await axios.post(`http://localhost:8080/auth/register`,   
                {
                    name: name.trim(),
                    email: email.trim().toLowerCase(),
                    password,
                }
            )
            if (response.status === 200) {
                setLoading(true);

                navigate('/user')
            }

        } catch (err) {
            const msg = err?.response?.data || err?.message || "Register failed";
            setError(typeof msg === "string" ? msg : JSON.stringify(msg));
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className='relative flex h-screen items-center justify-center'
            style={{
                backgroundImage: `url(${image})`,
                backgroundSize: "cover",
                backgroundPosition: "center",
                backgroundRepeat: "no-repeat",

            }}
        >

            <div className="absolute inset-0 bg-gradient-to-br from-black/70 via-black/50 to-orange-500/20" />

            <form
                onSubmit={register}
                className='rounded-2xl backdrop-blur-lg  border-border border-2 p-3 flex flex-col gap-y-10 min-w-sm'>

                <div className='flex justify-center'>
                    <h2 className='text-blue-400 text-3xl'> Register</h2>
                </div>

                {error && <p className='text-red-500 text-2xl'> {error}</p>}

                <div className='flex flex-col gap-y-10'>

                    <div className="relative">
                        <input
                            id="username"
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder=""
                            className="
                            peer w-full rounded-2xl
                            border border-white/20 bg-white/5
                            px-4 py-3.5 text-white
                            outline-none
                            placeholder-transparent
                            transition
                            focus:border-orange-400/70 focus:bg-white/10 focus:ring-2 focus:ring-orange-500/40
                            "
                        />

                        <label
                            htmlFor="username"
                            className="
                            pointer-events-none
                            absolute left-4 top-1/2 -translate-y-1/2
                            text-white/60 px-1
                            transition-all duration-200
                            peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-base
                            peer-focus:-top-2 peer-focus:text-xs peer-focus:text-orange-300
                            peer-not-placeholder-shown:-top-2 peer-not-placeholder-shown:text-xs
                            "
                        >
                            Username
                        </label>
                    </div>


                    <div className='flex flex-col'>
                        <label htmlFor="email">Email</label>
                        <input
                            className='border-2 border-white rounded-2xl p-2'
                            onChange={((e) => setEmail(e.target.value))}
                            type="email" id='email' name='email' placeholder='Enter email' />
                    </div>

                    <div className='flex flex-col'>
                        <label htmlFor="password">Password</label>
                        <input
                            className='border-2 border-white rounded-2xl p-2'
                            onChange={((e) => setPassword(e.target.value))}
                            type="password" id='password' name='password' placeholder='Enter password' />
                    </div>

                </div>

                <div className='flex justify-center'>

                    <button disabled={loading} type='submit' className='bg-blue-500 p-2 rounded-2xl'>

                        {loading ? "Creating account" : "Register"}

                    </button>
                </div>

            </form>

        </div>
    )
}

export default Register
