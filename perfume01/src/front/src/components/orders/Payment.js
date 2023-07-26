import React, {useEffect, useState} from "react";
import axios from "axios";
import {map} from "react-bootstrap/ElementChildren";
import Mypageitem from "../mypage/mypageitem";
import OrdersItem from "../orders/orders";
import Topimg from "../topimg/topimg";
import style from "./payment.module.css";
import styles from "../cart/cart.module.css";

const Payment = () => {
    const [totalPrice, setTotalPrice] = useState();
    const [member, setMember] = useState();
    const [memberName, setMemberName] = useState();
    const [phone, setPhone] = useState();
    const [email, setEmail] = useState();
    const [post, setPost] = useState();
    const [addr, setAddr] = useState();
    const [detail, setDetailAddr] = useState();
    const [point, setPoint] = useState();
    const [userPoint, setUserPoint] = useState();
    const [inputValue, setInputValue] = useState(0);
    const [resultPoint, setResultPoint] = useState();
    const [finalPoint, setFinalPoint] = useState();

    // 결재상품 정보
    useEffect(() => {
        let member_id = sessionStorage.getItem('loginID');
        axios
            .get(`http://localhost:8080/cart/main?member_id=${member_id}`)
            .then(response => {
                setMember(response.data);

                const total = response.data.reduce((sum, item) => {
                    return sum + item.product_price * item.product_count;
                }, 0);
                setTotalPrice(total);
            })
    }, [])

    // 결제자 정보
    useEffect(() => {
        let memberName = '';
        let member_id = sessionStorage.getItem('loginID');
        axios
            .get(`http://localhost:8080/member/myinfo?member_id=${member_id}`)
            .then(response => {
                setMemberName(response.data.member_name);
                setPhone(response.data.member_phone);
                setEmail(response.data.member_email);
                setPost(response.data.member_post);
                setAddr(response.data.member_basic_addr);
                setDetailAddr(response.data.member_detail_addr);
                setPoint(response.data.member_point)
            })
    })


    useEffect(() => {
        const jquery = document.createElement("script");
        jquery.src = "https://code.jquery.com/jquery-1.12.4.min.js";
        const iamport = document.createElement("script");
        iamport.src = "https://cdn.iamport.kr/js/iamport.payment-1.1.8.js";

        document.head.appendChild(jquery);
        document.head.appendChild(iamport);

        return () => {
            document.head.removeChild(jquery);
            document.head.removeChild(iamport);
        };
    }, []);

    const onClickPayment = () => {
        if (window.IMP) {
            window.IMP.init('imp07065242');
            // window.IMP.init('imp15577303');  // 내 가맹점 식별코드
            const data = {
                pg: 'kakaopay',
                pay_method: 'card',
                merchant_uid: `merchant_${new Date().getTime()}`,
                name: 'perfume 결제 테스트',
                amount: totalPrice - finalPoint,
                custom_data: {name: '부가정보', deps: '세부 부가정보'},
                buyer_name: memberName,
                buyer_tel: phone,
                buyer_email: email,
                buyer_addr: addr,
                buyer_postcode: post
            };
            window.IMP.request_pay(data, callback);
        }
    };

    const callback = (response) => {
        const {success, error_msg} = response;
        if (success) {
            alert('결제 성공');
        } else {
            alert(`결제 실패 : ${error_msg}`);
        }
    };


    const handleApplyPoints = () => {
        const uPoint = parseInt(userPoint, 10);
        const iPoint = parseInt(inputValue, 10);

        if (iPoint > uPoint) {
            alert('보유하신 포인트 보다 많이 입력할수 없습니다.')
            return;
        }
        setResultPoint(uPoint - iPoint);
        setFinalPoint(iPoint);
    };

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    }

    return (
        <>
            <Topimg/>

            <div className={style.paymentContainer}>
                <h1 className={style.paymentText}>Order Sheet</h1>
                <div className={style.paymentCustomerContainer}>
                    <div className={style.paymentCustomer_h2}>
                        <h2>주문자 정보</h2>
                        <p>*주문자 정보가 정확한지 확인하여 주십시오</p>
                    </div>
                    <div className={style.paymentCustomerArea}>
                        <div className={style.paymentCustomer}>
                            <dl>
                                <dd><strong>1</strong>. 이름 : <span>{memberName}</span></dd>
                                <dd><strong>2</strong>. 전화번호 : <span>{phone}</span></dd>
                                <dd><strong>3</strong>. 이메일 : <span>{email}</span></dd>
                                <dd><strong>4</strong>. 주소 : <span>{addr}, {detail}</span></dd>
                                <dd>*주소 변경시, Mypage에서 정보변경을 해주십시오</dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <hr/>

                <div className={style.ordersItemTable}>
                    <div className={style.ordersItemTable_h2}>
                        <h2>주문상품</h2>
                        <p>*결제하실 상품정보가 정확한지 확인하여 주십시오</p>
                    </div>
                    <div>
                        <OrdersItem></OrdersItem>
                    </div>
                </div>

                <hr/>

                <div className={style.totalPriceContainer}>
                    <div className={style.totalPriceContainer_h2}>
                        <h2>결제</h2>
                    </div>
                    <div className={style.totalPriceArea}>
                        <div className={style.pointPrice}>
                            <div className={style.pointBox}>
                                <p>사용하실 포인트를 입력해 주세요</p>
                                <p>사용가능한 포인트 : {point}</p>
                                <div className={style.pointBtnBox}>
                                    <label>
                                        <input type="text" placeholder="사용 포인트 입력" onChange={handleInputChange}/>
                                        <button className={style.pointBtn} onClick={handleApplyPoints}>적용</button>
                                    </label>
                                </div>
                            </div>

                            <div className={style.totalPrice}>
                                <div>상품액 {totalPrice} 원</div>
                                <div>-</div>
                                <div>사용포인트 {finalPoint || 0} 원</div>
                                <div>=</div>
                                <div>총 결제금액 {totalPrice - finalPoint || 0} 원</div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <button className={style.kakaopayBtn} onClick={onClickPayment}>카카오페이로 결제하기</button>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Payment;

