import useProducts from '../../hooks/product_hooks';
import style from '../itemmain/itemmain.module.css';
import axios from "axios";
import {Link} from "react-router-dom";
import React, { useRef } from 'react';

export default function ProductsItems({ categoryNo }) {
    const { products, loading, error } = useProducts();

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p>Error: {error.message}</p>;
    }

    const filteredProducts = categoryNo === 0 ? products : products.filter(product => product.tag_no === categoryNo);

    return (
        <>
            <ul className={style.bottom_list_big_container}>
                {filteredProducts.length > 0 ? (
                    filteredProducts.map((product) => (
                        <li key={product.product_no} className={style.bottom_list_small_container}>
                            <div className={style.bottom_list_img}>
                                <Link to={`/iteminfo/${product.product_no}`}>
                                    <img src={`http://localhost:8080/${product.product_mainimg}`} alt={product.product_name} />
                                </Link>
                            </div>
                            <div className={style.bottom_list_text}>
                                <div className={style.list_text_left}>
                                    {product.product_name}
                                </div>
                                <div className={style.list_text_right}>
                                    {product.product_price}
                                </div>
                            </div>
                        </li>
                    ))
                ) : (
                    <p>No products found.</p>
                )}
            </ul>
        </>
    );
}
