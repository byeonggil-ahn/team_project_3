import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch} from '@fortawesome/free-solid-svg-icons';
import style from '../header/header.module.css'


const Header = ({adminCk, setAdminCk}) => {

    useEffect(()=>{
        if (sessionStorage.getItem('loginName') === 'adminuser')
            setAdminCk = true;
    })

    return (
        <header id={style.header}>
            <div className={style.main_logo}>
                <Link to='/'>
                    <img className={style.loge_image} src={require('../../image/team_logo.png')} alt=""/>
                </Link>
            </div>
            <ul className={style.nav_menu}>
                <li className={style.nav_menu_item}><Link to="/">HOME</Link></li>
                <li className={style.nav_menu_item}><Link to="/about">ABOUT</Link></li>
                <li className={style.nav_menu_item}><Link to="/itemmain">PRODUCT</Link></li>
                <li className={style.nav_menu_item}><Link to="/mypage">MYPAGE</Link></li>
                <li className={style.nav_menu_item}><Link to="/cspage">C/S</Link></li>
                {adminCk === true && (
                    <li className={style.nav_menu_item}>
                        <Link to="http://localhost:8080/home">관리자</Link>
                    </li>
                )}
            </ul>
            <div className={style.nav_search}>
                <form className={style.search_form}></form>
                <input className={style.search_bar} type="text"/>
                <button type="submit"></button>
                <button className={style.search_btn}>
                    {/*<i className="fa-solid fa-magnifying-glass"><FontAwesomeIcon icon={faSearch}/></i>*/}
                </button>
            </div>
        </header>
    );
}

export default React.memo(Header);
