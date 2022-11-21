import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Register = (props) => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [confirmpass, setConfirmPass] = useState('');
    const [name, setName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
        if(name === '' || email === '' || pass === '' || confirmpass === '') {
            setErrorMessage('Please fill all fields');
        }
        else if(email.search("@usc.edu") === -1) {
            setErrorMessage('Please enter a USC email');
        }
        else if(pass !== confirmpass) {
            setErrorMessage('Passwords do not match');
        }
        else {
            navigate('../dashboard')
        }
    }

    return (
        <div className = "userAuthentication-container">
            <h2> Register </h2>
            {errorMessage && <div className="error-message"> {errorMessage} </div>}
            <form className = "register-form" onSubmit={handleSubmit}>
                <label htmlFor="name">Full Name</label>
                <input value={name} onChange={(e) => setName(e.target.value)} name="name" id="name" placeholder="Full Name"/>

                <label htmlFor="email">USC Email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="Tommytrojan@usc.edu" id="email" name="email" />

                <label htmlFor="password">Password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="Password" id="password" name="password" />
                
                <label htmlFor="confirmpassword">Confirm Password</label>
                <input value={confirmpass} onChange={(e) => setConfirmPass(e.target.value)} type="password" placeholder="Confirm Password" id="confirmpassword" name="confirmpassword" />
                <button>Register</button>
            </form>
            <Link to="/"> 
                <button className="link-btn">Already have an account? Log-In Here.</button>
            </Link>
        </div>
    );
};

export default Register;