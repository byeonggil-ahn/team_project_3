import React from 'react';
import style from '../footer/footer.module.css';

const Footer = () => {
    return (
        <footer id={style.footer}>
            <div className={style.footer_about}>
                <div className={style.footer_about_anker}>
                    <h3>ATEAM</h3>
                    <a href="subPage/Aboutus/index.html">About</a>
                    <a href="#">Guide</a>
                    <a href="#">Agreement</a>
                    <a href="#">Privacy Policy</a>
                </div>
                <div className={style.ptag}>
                    <h3>Banking Info</h3>
                    <p>국민 111111-11-111111</p>
                    <p>신한 11-11111-111</p>
                    <p>예금주: 백채린</p>
                </div>
            </div>
            <div className={style.footer_information}>
                <h3>Shop Information</h3>
                <p>AM 10:00 - PM 6:00, 주말 및 공휴일은 휴무입니다</p>
                <p>
                    상호 ateam, 대표 백채린<br />
                    캘리포니아 쿠퍼티노<br />
                    사업자 등록 번호 111-11-11111<br />
                    통신판매업번호 2023-몰루-0001호<br />
                    개인정보관리책임자 없음<br />
                    이메일 admin@xxx.com<br />
                </p>
            </div>
            <div className={style.footer_instagram}>
                <h3>Instagram by @ateam</h3>
                <ul className={style.footer_instagram_img}>
                    <li>
                        <img src={require('../../image/pexels-abdulrhman-alkady-6947682.jpg')} alt="" />
                    </li>
                    <li>
                        <img src={require('../../image/pexels-animesh-srivastava-7896916.jpg')} alt="" />
                    </li>
                    <li>
                        <img src={require('../../image/pexels-yusuf-arslan-3640668.jpg')} alt="" />
                    </li>
                    <li>
                        <img src={require('../../image/pexels-anis-salmani-11711835.jpg')} alt="" />
                    </li>
                    <li>
                        <img src={require('../../image/pexels-mathilde-langevin-10897665.jpg')} alt="" />
                    </li>
                    <li>
                        <img src={require('../../image/pexels-karolina-grabowska-8361486.jpg')} alt="" />
                    </li>
                </ul>
            </div>
        </footer>
    );
}

export default Footer;
