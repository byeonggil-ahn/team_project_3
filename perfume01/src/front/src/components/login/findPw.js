import React, {useEffect, useState} from 'react';
import axios from "axios";

const FindPw = () => {

    const [memberId, setMemberId] = useState('');
    const [memberEmail, setMemberEmail] = useState('');
    const [message, setMessage] = useState('');

    const handleFindPw = async () => {
        console.log(memberId, memberEmail);
        try {
            const response = await axios.post(`http://localhost:8080/member/findpw?member` ,{
                member_id: memberId,
                member_email: memberEmail
            });
            if (response.status === 200) {
                setMessage('비밀번호 재설정 성공');
            } else {
                setMessage('정보 불일치');
            }
        } catch (error) {
            console.error(error)
            setMessage('오류가 발생했습니다.')
        }
    };

    return (
        <>
            <div>
                <label htmlFor="member_id">아이디</label>
                <input
                    type="text"
                    id="member_id"
                    name="member_id"
                    value={memberId}
                    onChange={(e) => setMemberId(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="member_email">이메일</label>
                <input
                    type="text"
                    id="member_email"
                    name="member_email"
                    value={memberEmail}
                    onChange={(e) => setMemberEmail(e.target.value)}
                />
            </div>
            <button type="submit" onClick={handleFindPw}>비밀번호 찾기</button>
            <p>{message}</p>
        </>
    )
}

export default FindPw;