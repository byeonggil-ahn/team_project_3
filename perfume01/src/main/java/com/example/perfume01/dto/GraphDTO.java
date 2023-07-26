package com.example.perfume01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class GraphDTO {

    private int product_no;
    private String product_name;
    private String product_brand;
    private int product_sellcount;

}
