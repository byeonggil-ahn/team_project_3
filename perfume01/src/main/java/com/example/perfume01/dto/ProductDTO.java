package com.example.perfume01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class ProductDTO {
    private int product_no;
    private String product_name;

    int tag_no;

    private String product_brand;
    private int product_price;
    private int product_stock;
    private String product_content;
    private int product_delivery_price;
    private int product_sellcount;
    private String product_regdate;

//    private MultipartFile product_mainimg;
//    private MultipartFile product_subimg;

    // table 보관을 위한 File주소
    private String product_mainimg;
    private String product_subimg;

    // form의 업로드 정보를 전닯받기 위한 필드
    private MultipartFile product_mainimgf;
    private MultipartFile product_subimgf;



}
