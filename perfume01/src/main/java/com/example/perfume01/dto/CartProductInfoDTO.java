package com.example.perfume01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data @NoArgsConstructor
@Repository
public class CartProductInfoDTO {

    private String member_id, product_brand, product_name, product_mainimg;
    private int product_no, product_price, product_stock, product_count;

}
