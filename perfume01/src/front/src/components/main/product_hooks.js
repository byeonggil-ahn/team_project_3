import { useEffect, useState } from "react";

    export default function useProducts() {
        const [products, setProducts] = useState([]); //제품
        const [loading, setLoading] = useState(true); //로딩
        const [error, setError] = useState(null); //에러
      
        useEffect(() => {
          fetch(`data/products.json`)
            .then((res) => res.json())
            .then((data) => {
              console.log("fetching data");
              setProducts(data);
              setLoading(false);
            })
            .catch((error) => {
              setError(error);
              setLoading(false);
            });
        }, []);//[] - 언제 fetching을 할것인지 [](빈 배열)일 경우 처음 로딩 한번만 실행
        return { products, loading, error };
}