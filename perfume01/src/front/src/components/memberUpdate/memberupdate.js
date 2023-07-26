import React, {useEffect, useState} from 'react';
import DaumPostcode from "react-daum-postcode";
import style from './memberupdate.module.css';
import axios from "axios";
import Modal from "react-modal";
import Topimg from "../topimg/topimg";

const MemberUpdate = () => {

    const [myInfo, setMyInfo] = useState({}); // 내정보
    const [product, setProduct] = useState({});   // 상품
    const member_id = sessionStorage.getItem('loginID');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const handleModalOpen = () => {
        setIsModalOpen(true);
    }

    const handleModalClose = () => {
        setIsModalOpen(false);
    };

    const modalStyles = {
        content: {
            width: "600px",
            height: "500px",
            margin: "auto",
        },
    };

    const handleAddressSelect = (data) => {
        const { zonecode, roadAddress, jibunAddress } = data;

        document.querySelector("[name=member_post]").value = zonecode;
        document.querySelector("[name=member_basic_addr]").value = roadAddress || jibunAddress;

        handleModalClose();
    };

    useEffect(() => {
        axios.get(`http://localhost:8080/member/myinfo?member_id=${member_id}`)
            .then(response => {
                setMyInfo(response.data);
            })
            .catch((error)=>{
                console.error('내정보 불러오기에 실패했습니다.')
            })
    },[])

    // ======== 회원 정보 수정 ===========
    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData(document.getElementById('myForm'));

        try {
            const response =
                await axios.post('http://localhost:8080/member/changeInfo', formData, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            if (response.status === 200) {
                alert('회원 정보가 수정되었습니다.')
            }
        } catch (error) {
            console.error('회원정보 수정중 에러 발생')
        }
    }

    useEffect(()=>{
        Modal.setAppElement('#root');
    }, []);


    return (
        <>
            <Topimg/>
            <form id='myForm' onSubmit={handleSubmit} method="post" className={style.edit_member}>
                <input type="hidden" name="member_id" value={myInfo?.member_id}/>
                <div className={style.container}>
                    <h1 className={style.memberUpdateText}>회원 정보 변경</h1>
                </div>

                <div className={style.memberUpdateTable}>
                    <table >
                        <tbody>
                        <tr className={style.nick}>
                            <th>
                                <label>닉네임</label>
                            </th>
                            <td>
                                <input type="text" name="member_nick" placeholder={myInfo?.member_nick}/>
                                {/*<div className={valid_message}>사용 가능한 닉네임입니다</div>*/}
                                {/*<div className={invalid_message}>닉네임은 한글 또는 숫자 2~10자로 작성하세요</div>*/}
                                {/*<div className={invalid_message2}>이미 사용중인 닉네임입니다</div>*/}
                            </td>
                        </tr>

                        <tr  className={style.tel}>
                            <th>
                                <label>전화번호</label>
                            </th>
                            <td>
                                <input type="tel" name="member_phone" placeholder={myInfo?.member_phone}/>
                                {/*<div className={valid_message}>사용 가능한 전화번호입니다</div>*/}
                                {/*<div className={invalid_message}>올바른 전화번호가 아닙니다</div>*/}
                            </td>
                        </tr>

                        <tr className={style.email}>
                            <th>
                                <label>이메일</label>
                            </th>
                            <td>
                                <input type="email" name="member_email"  placeholder={myInfo?.member_email}/>
                                {/*<div className={valid_message}>사용 가능한 이메일 입니다</div>*/}
                                {/*<div className={invalid_message}>올바른 이메일 형식을 입력해주세요</div>*/}
                                {/*<div className={invalid_message2}>이미 사용중인 이메일입니다</div>*/}
                            </td>
                        </tr>

                        <tr className={style.post}>
                            <th>
                                <label>주소</label>
                            </th>
                            <td>
                                <input type="text" name="member_post"  placeholder={myInfo?.member_post} readOnly />
                                <button type="button" className={style.post_btn} onClick={handleModalOpen}>검색</button>
                            </td>

                        </tr>

                        <tr className={style.basic_post}>
                            <th></th>
                            <td >
                                <input type="text" name="member_basic_addr" placeholder={myInfo?.member_basic_addr} readOnly />
                            </td>
                        </tr>

                        <tr className={style.detail_post}>
                            <th></th>
                            <td >
                                <input type="text" name="member_detail_addr" required placeholder={myInfo?.member_detail_addr} />
                                <p className={style.post_p}>상세주소를 정확히 작성해주세요</p>
                            </td>
                        </tr>

                        <tr className={style.point}>
                            <th>
                                <label>보유 포인트</label>
                            </th>
                            <td>
                                <input type="text" name="member_point" required placeholder={myInfo?.member_point} value={myInfo?.member_point} readOnly/>
                            </td>
                        </tr>
                        <Modal isOpen={isModalOpen} onRequestClose={handleModalClose} style={modalStyles}>
                            <DaumPostcode onComplete={handleAddressSelect} />
                        </Modal>
                        </tbody>
                    </table>
                </div>
                <button className={style.saveBtn} type="submit" id="saveBtn">정보 수정</button>
            </form>
        </>
    )
}

export default MemberUpdate