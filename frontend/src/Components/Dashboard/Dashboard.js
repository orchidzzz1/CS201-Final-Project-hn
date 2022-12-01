import { useEffect, useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import { Container, Grid } from '@mui/material';

import { useDispatch, useSelector } from 'react-redux';
import { styled } from '@mui/material/styles';

import {actionFetchPosts } from "../../actions/actions";
import NavBar from "./NavBar/NavBar";
import Post from "../Posts/Post/Post";
import AllPosts from "../Posts/AllPosts";

const GridMainContainer = styled(Grid)(({ theme }) => ({
  borderRadius: 15,
  flexDirection: "row",
}));

const Dashboard = () => {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('profile')));

  const displayName = user?.displayName
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  const posts = useSelector((state) => (
    state.postReducer
  ));


  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem('profile')))
    localStorage.setItem('view', 0);
    
    if (displayName === -1 || displayName === null || displayName=== "-1") {
      console.log("not auth")
      navigate('/')
    }

    dispatch(actionFetchPosts());    

  }, [location, displayName, navigate, dispatch]);

  /*
  var posts = 
  [
    {
      "eventId": 6,
      "name": "f",
      "description": "f",
      "activityType": "Monday",
      "eventDateTime": "2022-12-01T17:32:00-08:00",
      "eventLocation": "foooo",
      "expired": false,
      "createdUserId": 1
    },
    {
      "eventId": 7,
      "name": "f",
      "description": "f",
      "activityType": "Monday",
      "eventDateTime": "2022-12-01T17:32:00-08:00",
      "eventLocation": "foooo",
      "expired": false,
      "createdUserId": 1
    }]
  */

  return (
      <Container maxWidth="xl">
          <NavBar/>
            <div style = {{position:'absolute', top:0, width: 1380}}>
              <h1 style={{marginTop: 75, textAlign: "left"}}>All Events</h1>
              <Container style={{marginTop: 50, marginBottom:50}} maxWidth="xl">

                <GridMainContainer container spacing={2} columns={4}>
                  {posts?.map((post) => (
                    <Grid item lg={1}>
                      <Post post={post} />
                    </Grid>
                  ))}
                </GridMainContainer>

              </Container>
          </div>
      </Container>
    
  );
  
};
export default Dashboard;