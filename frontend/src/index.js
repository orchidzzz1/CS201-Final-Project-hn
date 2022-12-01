import React from  'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import {  legacy_createStore as createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';

import { reducers } from './reducers';

import App from './App';

export const store = createStore(reducers, compose(applyMiddleware(thunk)))


const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);

root.render(
    <Provider store={store}>
        <App />
    </Provider>
);

