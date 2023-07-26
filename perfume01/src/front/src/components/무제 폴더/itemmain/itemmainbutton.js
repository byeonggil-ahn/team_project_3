import React from 'react';
import buttondata from './buttondata';

// 프롭스 전달받는거 다시해야함
const Itemmainbutton = (props) => {
    return (
        
                <button className="menu_button" >{props.name}
                    <span className="category__count">{props.count}</span>
                </button>

    )
}

export default Itemmainbutton;