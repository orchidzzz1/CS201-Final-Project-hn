// import { FETCH_ALL, FETCH_BY_SEARCH, CREATE, DELETE, RSVP } from '../constants/actionTypes';

// export default (state = { isLoading: true, posts: [] }, action) => {
//   switch (action.type) {
//     case FETCH_ALL:
//       return {
//         ...state,
//         posts: action.payload.data,
//         currentPage: action.payload.currentPage,
//         numberOfPages: action.payload.numberOfPages,
//       };
//     case FETCH_BY_SEARCH:
//     case FETCH_BY_CREATOR:
//       return { ...state, posts: action.payload.data };
//     case FETCH_POST:
//       return { ...state, post: action.payload.post };
//     case RSVP:
//       return { ...state, posts: state.posts.map((post) => (post._id === action.payload._id ? action.payload : post)) };
//     case CREATE:
//       return { ...state, posts: [...state.posts, action.payload] };
//     case DELETE:
//       return { ...state, posts: state.posts.filter((post) => post._id !== action.payload) };
//     default:
//       return state;
//   }
// };