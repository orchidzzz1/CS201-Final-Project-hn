import React, { useState} from 'react';
import { useDispatch } from "react-redux";
import { createPost } from "../../actions/actions";
import { useNavigate } from "react-router-dom";
import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import NavBar from '../Dashboard/NavBar/NavBar';
//import { Link, useNavigate } from 'react-router-dom';
//const initialState = {eventname: '', categories: '', description: '', date: '',  startTime: '', endTime: ' ', location:''};


const CreatePost = () => {
    const animatedComponents = makeAnimated();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [postData, setPostData] = useState({title: '', description: '', preferenceCategory: '', location: '',  eventDateTime: '', createdUserId:1});
    const [errorMessage, setErrorMessage] = useState('');

    const options = [
        { value: 'Club activity', label: 'Club activity' },
        { value: 'Sports', label: 'Sports' },
        { value: 'Study group', label: 'Study group' },
        { value: 'Monday', label: 'Monday' },
        { value: 'Tuesday', label: 'Tuesday' },
        { value: 'Wednesday', label: 'Wednesday' },
        { value: 'Thursday', label: 'Thursday' },
        { value: 'Friday', label: 'Friday' },
        { value: 'Saturday', label: 'Saturday' },
        { value: 'Sunday', label: 'Sunday' }
    ]


    const handleSubmit = (e) => {
        e.preventDefault();

        if(localStorage.getItem('authenticated') === null || localStorage.getItem('authenticated') === false) {
            setErrorMessage("You must log in before creating a post.");
        }
        else 
        {
            dispatch(createPost(postData, navigate));
            navigate('/dashboard');
        }
    };

    const handleOption = (e) => {
        setPostData({...postData, preferenceCategory:e.value})
    }
    
    return (
        <div>
            <NavBar />
            <div className="createpost-form">
                <h1> Create Event </h1>
                {errorMessage && <div className="error-message"> {errorMessage} </div>}
                <form className = "createpost-form" onSubmit={handleSubmit}>
                    <label htmlFor="eventName">Event Name </label>
                    <input value={postData.eventname} onChange={(e) => setPostData({ ...postData, title: e.target.value })} type="eventname" placeholder="Pick-Up Basketball" id="eventname" name="eventname" required/>
                    <label htmlFor="Categories"> Categories </label>
                    <Select onChange={e => handleOption(e)} styles={{ control: (baseStyles, state) => ({ ...baseStyles, width: '1420px', borderRadius: 10, }), }}  className="register" options={options} closeMenuOnSelect={false} components={animatedComponents}  />
                    <label htmlFor="Description"> Description </label>
                    <input value={postData.description} onChange={(e) => setPostData({ ...postData, description: e.target.value })} type="description" placeholder="we lowk suck, so go easy" id="description" name="description" required/>
                    <label htmlFor="Date">Date </label>
                    <input value = {postData.date} type ="date" id="date" name="date" required/>
                    <label htmlFor="Start Time">Start Time </label>
                    <input value = {postData.startTime} onChange={(e) => setPostData({ ...postData, eventDateTime: e.target.value })} type ="description" id="starttime" name="starttime" required/>
                    {/*<label htmlFor="End Time">End Time </label>
                    <input value = {postData.endTime} type ="time" id="endtime" name="endtime" required/>*/}
                    <label htmlFor="Location">Location </label>
                    <input value={postData.location} onChange={(e) => setPostData({ ...postData, location: e.target.value })} type="location" placeholder="USC Lyon Center" id="location" name="location" required/>
                    <button type="submit">Submit</button>
                </form>
            </div>
        </div>
        
    )
}

export default CreatePost;