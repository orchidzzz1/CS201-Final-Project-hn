import axios from 'axios';

const API =  axios.create({ baseURL: 'https://csci-201-project-368421.uw.r.appspot.com' });

export const APILogIn = (formData) =>  API.get(`/api/authenticate/${formData.email}/${formData.password}`);

export const createPost = (newPost) => API.post('/posts', newPost);

// export const fetchPosts = (page) => API.get(`/posts?page=${page}`);
// export const deletePost = (id) => API.delete(`/posts/${id}`);
// export const rsvpPost = (id) => API.patch(`/posts/${id}/rsvpPost`);

  