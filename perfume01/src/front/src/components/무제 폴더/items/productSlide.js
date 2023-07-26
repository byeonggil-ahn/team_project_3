import React, { useEffect, useState } from 'react';
import useProducts from '../../hooks/product_hooks';
import style from '../itemmain/itemmain.module.css';

export default function ProductSlide() {
  const { products, loading, error } = useProducts();
  const [bestItems, setBestItems] = useState([]);

  useEffect(() => {
    if (products.length > 0) {
      const filteredItems = products.filter(product => product.product_sellcount > 20);
      setBestItems(filteredItems);
    }
  }, [products]);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
      <>
        <ul className={style.slide_box}>
          {bestItems.map((product) => (
              <li key={product.no} datatype='2'>
                <a href=''>
                  <img src={`http://localhost:8080/${product.product_mainimg}`} alt="" />
                </a>
              </li>
          ))}
        </ul>
      </>
  );
}
