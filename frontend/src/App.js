import React, { useState } from "react";
// import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import './App.css';


import LogIn from "./Components/UserAuthentication/LogIn/LogIn";
import Register from "./Components/UserAuthentication/Register/Register";

const App = () => {
    const [currentID, setCurrentID] = useState('LogIn');

    const toggleForm =(formName) => {
        setCurrentID(formName);
    }

    return (
        <div className="App">
            {currentID === "LogIn" ? <LogIn onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />}
        </div>
    );
}

export default App; 