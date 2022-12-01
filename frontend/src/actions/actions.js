import { LOGIN } from '../constants/actionTypes';
import { CREATE } from '../constants/actionTypes'; 

import * as api from '../api/index.js';

export const actionLogIn = (formData, navigate) => async (dispatch) => {
  try {
    const { data } = await api.APILogIn(formData);
    
    dispatch({ type: LOGIN, data });
    
    navigate('/dashboard')

  } catch (error) {
    console.log(error);
  }
};


export const createPost = (post) => async (dispatch) => {
    try {
      //dispatch({ type: START_LOADING });
      const { data } = await api.createPost(post);
  
      dispatch({ type: CREATE, payload: data });
  
      //history.push(`/posts/${data._id}`);
    } catch (error) {
      console.log(error);
    }
  };

// export const rsvpPost = (id) => async (dispatch) => {
//     const user = JSON.parse(localStorage.getItem('profile'));
  
//     try {
//       const { data } = await api.rsvpPost(id, user?.token);
  
//       dispatch({ type: LIKE, payload: data });
//     } catch (error) {
//       console.log(error);
//     }
//   };

// export const deletePost = (id) => async (dispatch) => {
//     try {
//       await await api.deletePost(id);
  
//       dispatch({ type: DELETE, payload: id });
//     } catch (error) {
//       console.log(error);
//     }
//   };