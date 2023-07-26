package com.example.perfume01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class ProductCategoryStateDTO {

    private int tagNo, productCnt, sellMax, priceMax, priceMin;
    private float sellAvg, priceAvg;

}
