import React from 'react';
import { useSelector } from 'react-redux';

const ReduxDemo = () => {
    const counter = useSelector(state => state.counter);
    const flag = useSelector(state => state.flag);

    return (
        <div>
            <h1>Counter: {counter}</h1>

            {flag ? <h3>Flag was ON!</h3> : <h3>Flag was OFF!</h3>}
        </div>
    );
};

export default ReduxDemo;