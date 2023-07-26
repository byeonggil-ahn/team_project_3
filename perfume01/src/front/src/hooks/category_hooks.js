import { useEffect, useState } from "react";

    export default function useCategory() {
        const [category, setCategory] = useState([]); //카테고리
        const [loading, setLoading] = useState(true); //로딩
        const [error, setError] = useState(null); //에러
      
        useEffect(() => {
          fetch(`data/categoryList.json`)
            .then((res) => res.json())
            .then((data) => {
              console.log("fetching data");
              setCategory(data);
              setLoading(false);
              console.log(data);
            })
            .catch((error) => {
              setError(error);
              setLoading(false);
            });
        },[]);//[] - 언제 fetching을 할것인지 [](빈 배열)일 경우 처음 로딩 한번만 실행
        return { category, loading, error };
}