package com.example.perfume01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class OrdersDTO {
    // 주문 번호
    private int order_no;

    int product_id;
    int product_count;

    // 사용자로부터 입력을 받거나 DB로 입력 받을 값
    private String order_receiver;
    private String order_receive_phone;
    private String order_post;
    private String order_basic_addr;
    private String order_detail_addr;
    private String order_receiver_phone;
    private String order_request;
    private int order_userPoint;

    // 세션으로 가져올 값
    private String member_id;
    private String order_state;
    private Date order_regdate;
}
