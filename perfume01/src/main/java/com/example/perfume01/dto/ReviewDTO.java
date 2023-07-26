package com.example.perfume01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ReviewDTO {
    private int review_no;
    private String review_content;
    private int review_star;
    private String review_regdate;
    private String member_id;
    private int product_no;
}
