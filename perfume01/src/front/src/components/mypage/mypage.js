import React, {useEffect, useState} from 'react';
import Mypageitem from '../mypage/mypageitem';
import Topimg from '../topimg/topimg';
import style from '../mypage/mypage.module.css';
import axios from "axios";
import {Link} from "react-router-dom";
import Board from "../board/board";

const Mypage = () => {
    const [myInfo, setMyInfo] = useState();
    const member_id = sessionStorage.getItem('loginID');

    // 로그인한 유저 정보
    useEffect(()=>{
        axios
            .get(`http://localhost:8080/member/myinfo?member_id=${member_id}`)
            .then(response => {
                setMyInfo(response.data);
                console.log(response.data)
            })
            .catch((error)=>{
                console.error('내정보 불러오기에 실패했습니다.')
            })
    },[])


    return (
        <>
            <Topimg />

            <section>
                <div className={style.myPage_container}>
                    <h1 className={style.myPageText}>My Page</h1>
                    <div className={style.profileArea}>
                        <div className={style.profile}>
                            <ul className={style.member_info}>
                                {myInfo ? (<li>{myInfo.member_name} 님 <p>({myInfo.member_role})</p></li>)
                                    : (<li>Loading</li>)
                                }
                                {myInfo ? (<li><p>전화번호 : </p><p>{myInfo.member_phone}</p></li>)
                                    : (<li>Loading</li>)
                                }
                                {myInfo ? (<li><p>주소 : </p><p>{myInfo.member_basic_addr}, {myInfo.member_detail_addr}</p></li>)
                                    : (<li>Loading</li>)
                                }
                                {myInfo ? (<li><p>Email : </p><p>{myInfo.member_email}</p></li>)
                                    : (<li>Loading</li>)
                                }
                                {myInfo ? (<li className={style.point}><p>보유 포인트 : </p><p>{myInfo.member_point} p</p></li>)
                                    : (<li>보유 포인트 : Loading</li>)
                                }
                                <li>
                                    <Link to='/memberupdate'>
                                        <span className={style.memberupdate}>회원 정보 수정</span>
                                    </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <Board member_id={member_id}></Board>
                    <hr />
                    <div className={style.order_check_all}>
                        <div className={style.order}>
                            <h2>주문내역</h2>
                            <table>

                                <tr className={style.order_info}>
                                    <th></th>
                                    <th>상품 이름</th>
                                    <th>상품 가격</th>
                                    <th>배송 정보</th>
                                </tr>
                                <Mypageitem></Mypageitem>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
};

export default Mypage;