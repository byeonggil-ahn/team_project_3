import React from "react";


const Cspagebutton = ({ dataCategory, buttonName }) => {
    return (
        <>
            <button className="realbutton" data-category={dataCategory}>{buttonName}</button>
        </>
    )
}

export default Cspagebutton;