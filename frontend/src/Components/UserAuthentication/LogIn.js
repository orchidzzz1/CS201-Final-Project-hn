import React, { useState } from 'react';
import { Link, useNavigate }from 'react-router-dom';


const LogIn = (props) => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [authenticated, setauthenticated] = useState(localStorage.getItem(localStorage.getItem("authenticated")|| false));
    const users = [{ email: "tommytrojan@usc.edu", pass: "password" }];

    const handleSubmit = (e) => {
        e.preventDefault()
        const account = users.find((user) => user.email === email);
        if (account && account.pass === pass) {
            console.log(email)
            console.log(pass)
            setauthenticated(true)
            localStorage.setItem("authenticated", true);
            navigate('dashboard')
        }
    };
    
    return (
        <div className = "userAuthentication-container">
            <h2> Log In </h2>
            <form className = "login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">USC Email </label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="tommytrojan@usc.edu" id="email" name="email" />
                <label htmlFor="password">Password </label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="password" id="password" name="password" />
                <button>Log In</button>
            </form>
            <Link to="/register">
                <button className="link-btn">Don't have an account? Register Here.</button>
            </Link>
        </div>
    );
};


export default LogIn;

