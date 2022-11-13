import React, { useState } from 'react';

const Register = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [name, setName] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
    }

    return (
        <div className = "userAuthentication-container">
            <h2> Register </h2>
            <form className = "register-form"onSubmit={handleSubmit}>
                <label htmlFor="name">Full Name</label>
                <input value={name} onChange={(e) => setName(e.target.value)} name="name" id="name" placeholder="Full Name"/>

                <label htmlFor="email">USC Email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="Tommytrojan@usc.edu" id="email" name="email" />

                <label htmlFor="password">Password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="Password" id="password" name="password" />
                
                <button>Register</button>
            </form>
            <button className="link-btn" onClick={() => props.onFormSwitch("LogIn")}>Already have an account? Log-In Here.</button>
        </div>
    );
};

export default Register;