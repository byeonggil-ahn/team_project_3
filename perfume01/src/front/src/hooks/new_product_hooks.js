import { useEffect, useState } from "react";
import axios from "axios";

export default function useProducts() {
    const [products, setProducts] = useState([]); //제품
    const [loading, setLoading] = useState(true); //로딩
    const [error, setError] = useState(null); //에러

    useEffect(()=>{
        axios
            .get("http://localhost:8080/product/list")
            .then((response)=>{
                console.log("패칭 데이터");
                setProducts(response.data);
                setLoading(false);
            })
            .catch((error)=>{
                setError(error);
                setLoading(false);
            });
    },[]);

    return { products, loading, error };
}