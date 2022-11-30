import axios from 'axios';

const API =  axios.create({ baseURL: 'https://csci-201-project-368421.uw.r.appspot.com' });

export const APILogIn = (formData) =>  API.get(`/api/authenticate/${formData.email}/${formData.password}`);
export const APIRegisterUser = (formData) =>  API.post(`/api/registerUser`, formData);
export const createPost = (newPost) => API.get(`/api/addEvent/${newPost.title}/${newPost.description}/Monday/${newPost.location}/${newPost.eventDateTime}/${newPost.createdUserId}`);//, newPost);
export const APIFetchPosts = () => API.get(`api/getAllEvents`);
// export const APIFetchCuratedPosts = (id) => API.get(`/posts/${id}`);


  