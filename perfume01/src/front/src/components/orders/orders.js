import React, {useEffect, useState} from "react";
import style from '../orders/orders.module.css';
import axios from "axios";


const OrdersItem = () => {
    const member_id = sessionStorage.getItem('loginID');
    const [myCart, setMyCart] = useState([]);

    // 로그인한 유저의 상품 정보
    useEffect(() => {
        axios
            .get(`http://localhost:8080/cart/list?member_id=${member_id}`)
            .then(response => {
                setMyCart(response.data)
            })
            .catch((error)=>{
                console.error('장바구니 정보 불러오기에 실패했습니다.')
            })
    }, [])

    return (
        <>
            <table className={style.orderTable}>
                <tr className={style.orderInfo}>
                    <td colSpan={2} className={style.orderTableInfo}>상품정보</td>
                    <td className={style.orderTablePrice}>가격</td>
                    <td className={style.orderTableCount}>수량</td>
                </tr>
                {myCart.length > 0 ? (
                    myCart.map((item) => (

                        <tr
                            key={item.product_no}
                            className={style.order_main}
                        >
                            <td className={style.orderImgTd}>
                                <img className={style.orderImg} src={`http://localhost:8080/${item.product_mainimg}`} alt="" />
                            </td>
                            <td className={style.orderItemName}>{item.product_name}</td>
                            <td className={style.orderItemPrice}>{item.product_price} 원</td>
                            <td className={style.orderItemCount}>{item.product_count} 개</td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan={4}>장바구니가 비어있습니다.</td>
                    </tr>

                )}
            </table>
        </>
    );
};

export default OrdersItem;