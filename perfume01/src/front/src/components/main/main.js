import React from 'react';
// import { MainpageItem1, MainpageItem2 } from '../elements/mainpageitem';
import Products from '../items/productList'
import NewProducts from '../items/productNew'
import Topimg from '../topimg/topimg';
import style from '../main/main.module.css';
import axios from "axios";
import {useEffect, useState} from "react";

const Main = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios
            .get('http://localhost:8080/product/list')
            .then((response) => {
                setProducts(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    // const items = [MainpageItem1, MainpageItem2];
    // // 다른 아이템 추가할 시 ,mainpageitem에 함수 생성수 배열에 집어넣어서 map 사용

    return (
        <main>
            <Topimg/>
            
            <section id={style.discription_story}>
                <div className={style.discriptino_story_text}>
                    <h2>자연을 품으며 자연을 지키는 향수</h2>
                    <p>
                        저희 Ateam은 자연의 가치를 중요하게 생각합니다.<br />
                        자연에서부터 그 향을 빌려온다는 생각으로 친환경적인 재료와 방법으로
                        <br />자연과 공존하며 자연의 향을 품을 수 있도록 노력하겠습니다.
                    </p>
                    <div>

                    </div>
                </div>
                <div className={style.discription_content}>
                    <div className={style.discription_content_left}></div>
                    <div className={style.discription_content_center}>
                        <div className={style.discription_content_center_top}></div>
                        <div className={style.discription_contenst_center_bottom}></div>
                    </div>
                    <div className={style.discription_content_right}></div>
                </div>

            </section>

            <section id={style.best_seller}>
                <div className={style.best_seller_title}>
                    <h2>BEST SELLER</h2>
                </div>
                 <Products/>
            </section>

            <section id={style.new_arrival}>
                <div className={style.new_arrival_title}>
                    <h2>NEW ARRIVAL</h2>
                </div>
                <NewProducts/>
            </section>

        </main>
    )
}

export default Main;