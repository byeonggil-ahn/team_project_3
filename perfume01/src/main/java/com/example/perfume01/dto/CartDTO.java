package com.example.perfume01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private String member_id, product_brand, product_name, product_mainimg;
    private int product_no, product_price, product_stock, product_count;

}
