import { LOGIN, CREATE, REGISTER, FETCH_ALL, FETCH_CURATED } from '../constants/actionTypes';

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

export const actionRegisterUser = (formData, navigate) => async (dispatch) => {
  try {
    const { data } = await api.APIRegisterUser(formData);

    dispatch({ type: REGISTER, data});

    navigate('/dashboard')

  } catch(error) {
    console.log(error);
  }
}


export const createPost = (post, navigate) => async (dispatch) => {

  try {
    const { data } = await api.createPost(post);

    dispatch({ type: CREATE, payload: data });


    navigate('/dashboard');

  } catch (error) {
    console.log(error);
  }
};


  export const actionFetchPosts = () => async (dispatch) => {
    try {
      const { data } = await api.APIFetchPosts();

  
      dispatch({ type: FETCH_ALL, payload: data });
  
    } catch (error) {
      console.log(error);
    }
  };

  export const actionFetchCurated = (post) => async (dispatch) => {
    try {
      const { data } = await api.createPost(post);
  
      dispatch({ type: FETCH_CURATED, payload: data });
  
    } catch (error) {
      console.log(error);
    }
  };

