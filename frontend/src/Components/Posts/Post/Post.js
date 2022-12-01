import React from 'react';
//import { Link, useNavigate }from 'react-router-dom';
import Card from '@mui/material/Card';
import { CardActionArea } from '@mui/material';
import CardContent from '@mui/material/CardContent';





const Post = (post) => {
    //populate the form with an API call
    // const posts = useSelector((state) => state.posts);

    // hardcoded just for testing 
    const postData = {eventname: 'Basketball', categories: 'Sports, Gym', description: 'Redekopp broke our ankles last time', startTime: '12:00 PM', endTime: '4:00 PM', location: 'USC Lyon Center'};
    let s = JSON.stringify(post);
    let p = JSON.parse(s);

    console.log(p);  

    return (
        <Card raised>
            <CardActionArea>
                <CardContent>
                    <h1>{post['post'].name}</h1>
                    <p>Categories: {post['post'].activityType}</p>
                    <p>Description: {post['post'].description}</p>
                    <p>Start: {post['post'].eventDateTime}</p>
                    {/*<p>End: {postData.endTime}</p>*/}
                    <p>Location: {post['post'].eventLocation}</p>

                </CardContent>
            </CardActionArea>
        </Card>
    )
}

export default Post;