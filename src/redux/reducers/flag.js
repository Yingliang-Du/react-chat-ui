const flagReducer = (state = false, action) => {
    switch(action.type) {
        case 'ON':
            return true;
        case 'OFF':
            return false;
        default:
            return state;
    }
};

export default flagReducer;