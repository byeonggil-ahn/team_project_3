import React, {useEffect, useState} from "react";
import DaumPostcode from "react-daum-postcode";
import {Link, useNavigate} from "react-router-dom";
import style from '../joinus/joinus.module.css';
import Modal from 'react-modal';
import axios from "axios";


const Joinus = () => {
    const navigate = useNavigate();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [memberId,setMemberId]=useState([]);
    const [inputId,setInputId]=useState("");



    const [emailInput,setEmailInput] = useState('');
    const emailhandle = (event) => {
        if (event.target.value != "") {
            setEmailInput(event.target.value)
        } else {
            setEmailInput('')
        }
    }


    useEffect(()=>{
        Modal.setAppElement('#root');
    }, []);


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
        const memberPw = formData.get('member_pw');
        const verifyPw = formData.get('verify_pw');
        
        if (memberPw !== verifyPw) {
            alert("비밀번호가 일치하지않습니다 다시입력해주세요");
            return; // Stop the form submission
        }
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

    useEffect(() => {
        axios
            .get("http://localhost:8080/member/selectlist")
            .then((response) => {
                const memberIds = response.data.map((member) => member.member_id);
                setMemberId(memberIds);
            })
            .catch((error) => {
                console.error("중복확인 데이터 받아오는 에러입니다" + error);
            });
    }, []);


    const inputIdFunction = (e) => {
    setInputId(e.target.value);
    }

    const checkId = () => {
        if (memberId.includes(inputId)) {
            alert("이미 사용중인 아이디입니다");
            setInputId("");
        } else {
            alert("사용할 수 있는 아이디입니다");
        }
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
                                        <input type="text" className="id" name="member_id"  onChange={inputIdFunction} value={inputId} required />
                                        <button onClick={checkId}>중복확인</button>
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
                                        <input type="email" name="member_email" required />
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