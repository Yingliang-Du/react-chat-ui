import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { increment, decrement } from '../redux/actions'

const ReduxDemo = () => {
    const counter = useSelector(state => state.counter);
    const flag = useSelector(state => state.flag);

    const dispatch = useDispatch();

    return (
        <div>
            <h1>Counter: {counter}</h1>
            <button onClick={() => dispatch(increment())}>+</button>
            <button onClick={() => dispatch(decrement())}>-</button>

            {flag ? <h3>Flag was ON!</h3> : <h3>Flag was OFF!</h3>}
        </div>
    );
};

export default ReduxDemo;