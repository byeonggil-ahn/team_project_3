import React, {useEffect, useState} from 'react';
import Topimg from '../topimg/topimg';
import { Link, useNavigate } from 'react-router-dom';
import style from '../login/login.module.css';
// import NaverLogin from './NaverLogin';
import axios from "axios";

const Login = ({isLoggedIn, setIsLoggedIn ,adminCk,setAdminCk}) => {
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    // const [isLoggedIn, setIsLoggedIn] = useState(false);
    // 쿠키 저장
    const getCookie = (name) => {
        const cookieName = name + '=';
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            let cookie = cookies[i];
            while (cookie.charAt(0) === ' ') {
                cookie = cookie.substring(1);
            }
            if (cookie.indexOf(cookieName) === 0) {
                return cookie.substring(cookieName.length, cookie.length);
            }
        }
        return '';
    };



    // useEffect(() => {
    //     // 세션 ID를 쿠키에서 가져온다.
    //     const sessionID = getCookie('JSESSIONID');
    //
    //     // 세션 ID가 있는 경우에만 요청을 보냄
    //     if (sessionID) {
    //         axios.post('http://localhost:8080/member/login', {}, {
    //             headers: {
    //                 'X-Requested-With': 'XMLHttpRequest', // CORS 요청을 위한 헤더 추가
    //                 'Content-Type': 'application/json', // 요청 컨텐츠 타입
    //                 'Cookie': `JSESSIONID=${sessionID}` // 세션 ID를 쿠키로 포함시킴
    //             }
    //         })
    //             .then((response) => {
    //                 console.log(response.data); // 데이터 콘솔 출력
    //                 setIsLoggedIn(true);
    //             })
    //             .catch(error => {
    //                 console.error('로그인 상태 확인에 실패하였습니다.', error);
    //             });
    //     }
    // }, []);

    const handleIdChange = (e) => {
        setId(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Add your login logic here
        const data = {
            member_id: id,
            member_pw: password,
        };
        axios.post('http://localhost:8080/member/login', data, {withCredentials: true,})
            .then(response => {
                // 세션 ID를 쿠키에 저장
                setIsLoggedIn(true); // 로그인 상태 변경
                sessionStorage.setItem('loginID', response.data.loginID);
                sessionStorage.setItem('loginName', response.data.loginName)
                alert('로그인 성공')
                navigate('/');
            })
            .catch((error) => {
                console.error(error);
                alert('로그인시 에러가 발생하였습니다.');
            });
    };

    return (
        <>
            <Topimg />
            <section>
                <div className={style.main_container}>
                    <div className={style.login_container}>
                        <h1 className={style.login}>Login</h1>
                        <form onSubmit={handleSubmit}>
                            <div className={`${style.box} ${style.member_box}${style.show}`}>
                                <div className={style.login_id}>
                                    <input
                                        type="text"
                                        className={style.member_id}
                                        name="id"
                                        placeholder="ID"
                                        value={id}
                                        onChange={handleIdChange}
                                        required
                                    />
                                    <p className={style.warning_id} style={{ display: 'none', color: 'red', fontSize: 'smaller' }}>
                                        아이디가 입력되지 않았습니다.
                                    </p>
                                </div>
                                <div className={style.login_pw}>
                                    <input
                                        type="password"
                                        className={style.member_pw}
                                        name="pw"
                                        placeholder="PASSWORD"
                                        value={password}
                                        onChange={handlePasswordChange}
                                        required
                                    />
                                    <p className={style.warning_pw} style={{ display: 'none', color: 'red', fontSize: 'smaller' }}>
                                        비밀번호가 입력되지 않았습니다.
                                    </p>
                                </div>
                            </div>
                            <div className={style.login_enter}>
                                <button type="submit" className={style.login_login} id="send">
                                    로그인
                                </button>
                            </div>
                        </form>

                        <div className={style.login_middle}>
                            <a href="/findPw" className={style.find}>
                                ID/PW 찾기
                            </a>
                            <Link to='/joinus' className={style.join}>
                                회원가입
                            </Link>
                        </div>

                        {/*<span>OR</span>*/}

                        {/*<div>*/}
                        {/*    /!* <img src={require('../../image/컬러네이버.png')} alt="네이버" /> *!/*/}
                        {/*    /!* <NaverLogin/> *!/*/}
                        {/*    <img src={require('../../image/컬러카카오.png')} alt="카카오" />*/}
                        {/*    <img src={require('../../image/컬러구글.png')} alt="구글" />*/}
                        {/*</div>*/}
                    </div>
                </div>
            </section>
        </>
    );
};

export default Login;
