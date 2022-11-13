import React, { useState} from 'react';

const LogIn = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
    }

    return (
        <div className = "userAuthentication-container">
            <h2> Log In </h2>
            <form className = "login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">USC Email </label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="Tommytrojan@usc.edu" id="email" name="email" />
                <label htmlFor="password">Password </label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="Password" id="password" name="password" />
                <button onClick={() => props.onFormSwitch("CreatePost")}>Log In</button>
            </form>
            <button className="link-btn" onClick={() => props.onFormSwitch("Register")}>Don't have an account? Register Here.</button>
        </div>
    );
};


export default LogIn;

