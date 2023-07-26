import React from 'react';
import useProducts from '../main/product_hooks';
import style from '../main/main.module.css';

export default function ProductsList() {
  const { products, loading, error } = useProducts();

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <>
      <ul className={style.best_seller_items}>
        {products.map((product) => (
          <li key={product.product_no} className={style.best_seller_item} datatype='2'>
            <a href=''>
              <div className={style.item_thumbnail}>
            <img src={require("../../image/pexels-karolina-grabowska-8361484.jpg")}/>
              <div className={style.discription_detail}>
                <h2>{product.product_name}</h2>
                <p>{product.product_content}</p>
              </div>
              </div>
            </a>     
          </li>
        ))}
      </ul>
    </>
  );
}
