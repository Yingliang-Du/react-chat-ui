export const increment = (by) => {
    return {
        type: 'INCREMENT',
        payload: by
    }
};

export const decrement = () => {
    return {
        type: 'DECREMENT'
    }
};