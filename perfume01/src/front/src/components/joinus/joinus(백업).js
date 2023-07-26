import React, {useEffect, useState} from "react";
import DaumPostcode from "react-daum-postcode";
import {Link, useNavigate} from "react-router-dom";
import style from '../joinus/joinus.module.css';
import Modal from 'react-modal';
import axios from "axios";


const Joinus = () => {

    useEffect(()=>{
        Modal.setAppElement('#root');
    }, []);

    const navigate = useNavigate();

    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleModalOpen = () => {
        setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
    };

    const handleAddressSelect = (data) => {
        const { zonecode, roadAddress, jibunAddress } = data;

        document.querySelector("[name=member_post]").value = zonecode;
        document.querySelector("[name=member_basic_addr]").value = roadAddress || jibunAddress;

        handleModalClose();
    };

    const modalStyles = {
        content: {
            width: "600px",
            height: "500px",
            margin: "auto",
        },
    };

    // 폼데이터 관련
    const handleSubmit = (e)=>{
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        axios
            .post("http://localhost:8080/member/reg_user", formData)
            .then((response)=>{
                alert("성공적으로 회원가입이 되었습니다. 로그인 페이지로 이동합니다.")
                navigate("/login");
            })
            .catch((error)=>{
                console.error("회원가입에 실패하였습니다. 관리자에게 문의해주세요.", error)
            });
    };

    return (

        <section className={style.main}>
            <h1 className={style.join_us}>Join Us</h1>

            <form onSubmit={handleSubmit}>
                <div id={style.injoy}>
                    <table>
                        <tbody>
                            <tr>
                                <td>ID</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.userId}>
                                        <input type="text" className="id" name="member_id" required />
                                        <button>중복확인</button>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td>NAME</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.userName}>
                                        <input type="text" className="name" name="member_name" required />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td>닉네임</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.userName}>
                                        <input type="text" className="name" name="member_nick" required />
                                    </div>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>PASSWORD</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.userPw}>
                                        <input type="password" className="pw" name="member_pw" required />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td>VERIFY PASSWORD</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.userPw}>
                                        <input type="password" className="pw" name="verify_pw" required />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td>E-MAIL</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.email}>
                                        <input type="text" name="member_email" required /> @
                                        <select name="email_domain">
                                            <option value="naver.com">naver.com</option>
                                            <option value="daum.net">daum.net</option>
                                            <option value="gmail.com">gmail.com</option>
                                        </select>
                                    </div>
                                    <input type="checkbox" /> 이메일 수신 동의
                                </td>
                            </tr>

                            <tr>
                                <td>PHONE NUMBER</td>
                                <td className={style.joinusInputTd}>
                                    <div className={style.userPhone}>
                                        <input type="text" minLength="11" maxLength="11" size="15" name="member_phone" required />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td>우편번호</td>
                                <td className={style.joinusInputTd}>
                                    <div>
                                        <input type="text" name="member_post" placeholder="우편번호" readOnly/>
                                        <button type="button" className="find-address-btn" onClick={handleModalOpen}>검색</button>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>기본주소</td>
                                <td className={style.joinusInputTd}>
                                    <div>
                                        <input type="text" name="member_basic_addr" placeholder="기본주소"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>상세주소</td>
                                <td className={style.joinusInputTd}>
                                    <div>
                                        <input type="text" name="member_detail_addr" placeholder={"상세주소"}/>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <p className={style.terms}>
                        <input type="checkbox" required />
                        <Link to='/terms' target='_blank' >
                            개인정보 처리방침
                        </Link>
                        에 따라 개인 정보를 수집, 사용, 타사에 대한 제공 및 처리하는 데 동의합니다.
                    </p>

                    <hr className={style.under_terms} />

                    <button className={style.injoy_btn} type={"submit"} >
                        회원가입
                    </button>

                    <Modal isOpen={isModalOpen} onRequestClose={handleModalClose} style={modalStyles}>
                        <DaumPostcode onComplete={handleAddressSelect} />
                    </Modal>

                </div>
            </form>
        </section>
    );
}

export default Joinus;