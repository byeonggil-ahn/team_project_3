import React, {useEffect, useState} from "react";
import style from './iteminfo.module.css';
import {Link, useParams, useNavigate} from "react-router-dom";
import axios, {get} from "axios";
import Board from "../board/board";
import board from "../board/board";

const Iteminfo = () => {
    const [modalOpen, setModalOpen] = useState(false);
    const [product, setProduct] = useState();   //상품
    const {product_no} = useParams();
    const [memberId, setMemberId] = useState(null);
    const [qty, setQty] = useState(1);
    const navigate = useNavigate();

    const instance = axios.create({
        baseURL: "http://localhost:8080",
        withCredentials: true,
    });
    const fetchUserInfo = async () => {
        try {
            const response = await instance.get("/member/userinfo");
            const data = response.data;
            console.log(data);
        } catch (error) {
            console.error("유저 정보를 가져오는 중 오류 발생", error);
        }
    }

    const openModal = () => {
        setModalOpen(true);
    };
    const closeModal = () => {
        setModalOpen(false);
    };

    // 장바구니에 상품 추가
    // 일단 하드코딩 해놓은 상태이기에 동적으로 설정해야함
    const addToCart = async (product_no) => {
        const member_id = sessionStorage.getItem('loginID');

        try {
            await axios.post(`http://localhost:8080/cart/insert`, {
                member_id: member_id,
                product_no: product_no,
                product_count: qty,
            })
            alert('장바구니에 상품을 담았습니다.')
        } catch (error) {
            console.error("장바구니에 상품을 담는중 에러 발생")
        }
    };

    const handleQtyChange = (value) => {
        setQty(parseInt(value));
    }


    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/product/detail?product_no=${product_no}`);
                const data = response.data;
                setProduct(data);
            } catch (error) {
                console.error('상품 데이터를 가져오는 중 오류가 발생했습니다.', error);
            }
        };
        fetchProduct();
    }, [product_no]);

    if (!product) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <form>
                <div className={style.iteminfo_wrapper}>
                    <div className={style.iteminfo_imgbox}>
                        <div className={style.product_mainimg}>
                            {product.product_mainimg && (
                                <img src={`http://localhost:8080/${product.product_mainimg}`} alt="" />
                            )}
                        </div>
                        {/*<div className={style.product_subimgbox}>*/}
                        {/*    <div></div>*/}
                        {/*    <div></div>*/}
                        {/*    <div></div>*/}
                        {/*    /!* 서브이미지 갯수대로 map돌리기 *!/*/}
                        {/*</div>*/}
                    </div>
                    <div className={style.iteminfo_textbox}>
                        <div className={style.product_name}>
                            상품명 : {product.product_name}
                        </div>
                        <div className={style.product_price}>
                            가격 : {product.product_price}
                        </div>
                        <div className={style.product_content}>
                            상품 세부 정보 : {product.product_content}
                        </div>
                        <div className={style.product_delivery_price}>
                            배송비 : {product.product_delivery_price}
                        </div>
                        <div className={style.stock_and_button}>
                            <input
                                id="product_Stock"
                                type="number"
                                min="1"
                                max={10}
                                defaultValue={1}
                                onChange={e=>{handleQtyChange(e.target.value)}}/>

                                <button onClick={()=>addToCart(product_no)} className={style.iteminfo_cart}>장바구니</button>

                            <button className={style.iteminfo_buy}>구매</button>
                        </div>
                    </div>
                </div>
            </form>

            <img src={require('../../image/iteminfo_bigmimg.jpg')} alt="" style={{ width: '1100px', margin: '0 auto' }} />

            <Board></Board>
        </>
    );
}

export default Iteminfo;
