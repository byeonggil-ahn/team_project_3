import React, {useEffect} from 'react';
import useProducts from '../../hooks/best_product_hooks';
import style from '../main/main.module.css';
import axios from "axios";

export default function ProductsList() {
  const { products, loading, error } = useProducts();

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  const filterProduct = products.filter((product)=>product.product_sellcount >= 50);

  return (
    <>
      <ul className={style.best_seller_items}>
        {filterProduct.map((product) => (
          <li key={product.product_no} className={style.best_seller_item} datatype='2'>
            <a href=''>
              <div className={style.item_thumbnail}>
            <img src={`http://localhost:8080/${product.product_mainimg}`}/>
              <div className={style.discription_detail}>
                <h2>{product.product_name}</h2>
                <p>{product.product_content}</p>
              </div>
              </div>
            </a>
              <div className={style.discription_item}>
              <div className={style.discription_item_name}>{product.product_name}</div>
              <div className={style.discription_item_price}>{product.product_price}</div>
            </div>      
          </li>
        ))}
      </ul>
    </>
  );
}
