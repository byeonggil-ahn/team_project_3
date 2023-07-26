import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import style from './cart.module.css';
import axios from "axios";
import Topimg from "../topimg/topimg";


// import * as events from "events";
// import {render} from "@testing-library/react";

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);
    const [cartCount, setCartCount] = useState(1);
    const [isEmpty, setIsEmpty] = useState(0);
    const [member_id, setMember_id] = useState();
    const [memberInfo, setMemberInfo] = useState(0);
    let [totalPrice, setTotalPrice] = useState(0);
    const [userPoint, setUserPoint] = useState();

    useEffect(() => {
        const member_id = sessionStorage.getItem('loginID');
        //장바구니 정보 요청
        axios.get(`http://localhost:8080/cart/main?member_id=${member_id}`)
            .then(response => {
                setCartItems(response.data);
            })
            .catch(error => {
                console.log("장바구니 정보 요청 에러");
            });
    }, []);

    // 포인트 조회를 위한 맴버조회
    useEffect(()=>{
        const member_id = sessionStorage.getItem('loginID');
        //장바구니 정보 요청
        axios.get(`http://localhost:8080/member/userinfo?member_id=${member_id}`)
            .then(response => {
                setMemberInfo(response.data);
                setUserPoint(response.data.member_point)
            })
            .catch(error => {
                console.log("유저 정보 요청 에러");
            });
    }, []);

    // 총 상품 가격
    useEffect(() => {
        // 상품 가격 총합 계산
        const total = cartItems.reduce((acc, item) => acc + item.product_price * item.product_count, 0);
        setTotalPrice(total);
    }, [cartItems]);

    const handleQtyChange = (event, index) => {
        const {value} = event.target;

        setCartItems(prevItems => {
            const updatedItems = [...prevItems];
            updatedItems[index].product_count = parseInt(value);
            return updatedItems;
        })
    }

    const handleDelete = (product_no) => {
        // 상품 삭제 API 요청
        const member_id = sessionStorage.getItem('loginID');
        axios.delete(`http://localhost:8080/cart/delete?product_no=${product_no}&member_id=${member_id}`)
            .then(response => {
                axios.get(`http://localhost:8080/cart/main?member_id=${member_id}`)
                    .then(response => {
                        setCartItems(response.data);
                        alert('장바구니에서 상품이 삭제되었습니다.')
                    })
                    .catch(error => {
                        console.log("장바구니 정보 요청 에러");
                    });
            })
            .catch(error => {
                console.log("상품 삭제 에러");
            });
    };

    const handleApplyPoints = () => {
        // 포인트 적용 API 요청
        // ...
    };

    const handleCheckout = () => {
        // 결제 진행 로직 (우선 단순 페이지 이동)
    };


    return (
        <>
            <Topimg/>
            <div className={style.cart_allcontainer}>
                <h1 className={style.cartText}>Cart</h1>
                <div>
                    <table className={style.cart_table}>
                        <thead className={style.cart_table_thead}>
                        <tr>
                            <td>상품</td>
                            <td>상품이름</td>
                            <td>수량</td>
                            <td>가격</td>
                            <td>상품체크</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            /* db에서 데이터 넘어오면 tr,td 동적으로 생성 */
                            cartItems.map(item => (
                                <tr key={item.product_no}>
                                    <td>
                                        <img className={style.productImg} src={`http://localhost:8080/${item.product_mainimg}`}/>
                                    </td>
                                    <td className={style.productName}>{item.product_name}</td>
                                    <td>
                                        <input
                                            type="number"
                                            min="1"
                                            max="10"
                                            value={item.product_count}
                                        />
                                    </td>
                                    <td className={style.productPrice}>{item.product_price} 원</td>
                                    <td>
                                        <button onClick={() => handleDelete(item.product_no)}>삭제</button>
                                    </td>
                                </tr>
                            ))
                        }
                        </tbody>
                    </table>

                </div>

                <hr className={style.cartHr}/>

                <div className={style.cart_bottomcontainer}>
                    <div className={style.cart_bottomleft_container}>

                        <div className={style.cart_box}>판매자에게 문의</div>
                        <p>상품관련으로 문의하실 사항이 있으시면 아래 박스에 내용을 작성해주세요</p>
                        <textarea className={style.cart_bottom_textarea}></textarea>
                    </div>
                    <div className={style.cart_bottomrightcontainer}>
                        <div className={style.cart_box}>주문 내역</div>
                        <div>
                            <p>배송비 및 추가 비용은 입력된 값을 기준으로 계산됩니다</p>
                            <ul>
                                <li className={style.cart_bottomright_totalprice}>
                                    <strong>보유 포인트</strong>
                                    <strong>{userPoint} 원</strong>

                                </li>

                                <li className={style.cart_bottomright_price}>
                                    <strong>상품가격</strong>
                                    {/*{cartItems.map(item => (totalPrice + item.product_price*1) )}*/}

                                    <strong>{totalPrice} 원</strong>
                                </li>
                                <p className={style.pointP}>*포인트는 결제 페이지에서 사용가능</p>
                            </ul>


                        </div>
                    </div>
                </div>
                <Link to='/Payment'>
                    <button className={style.cart_payment} onClick={handleCheckout}>주문하기</button>
                </Link>
            </div>
        </>
    );
}

export default Cart;
