import React, { useEffect } from "react";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';

import LogIn from "./Components/UserAuthentication/LogIn";
import Register from "./Components/UserAuthentication/Register";
import CreatePost from "./Components/CreatePost/CreatePost";
import Dashboard from "./Components/Dashboard/Dashboard";
import PostPage from "./Components/PostPage/PostPage";


const App = () => {
    // const [currentID, setCurrentID] = useState('LogIn');

    // const toggleForm =(formName) => {
    //     setCurrentID(formName);
    // }


    return (
        // <div className="App">
        //     {currentID === "LogIn" ? <LogIn onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />}
        // </div>  
        <div className="App">
            <BrowserRouter>
                <Routes>
                    {
                        /*
                        Maybe have the '/' directory first check if you're logged in
                        If you are authenticated, then go to dashboard
                        If you aren't, go to login
                        */
                    }
                    <Route path="/" exact element={<LogIn/ >} />
                    <Route path="/register" exact element={<Register/ >} />
                    <Route path="/createpost" exact element={<CreatePost/ >} />
                    <Route path="/dashboard" exact element={<Dashboard/ >} />
                    {/* <Route path="/post" exact element={<PostPage/ >} /> */}
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App;


