import { combineReducers } from 'redux';

import counter from './counter';
import flagReducer from './flag';

const allReducers = combineReducers({
    counter: counter,
    flag: flagReducer
});

export default allReducers;