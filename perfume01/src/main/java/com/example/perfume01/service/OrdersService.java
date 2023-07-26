package com.example.perfume01.service;

import com.example.perfume01.dto.OrdersDTO;

import java.util.List;

public interface OrdersService {

    int insertOrder(OrdersDTO dto);

    List<OrdersDTO> selectList(OrdersDTO dto);

    OrdersDTO selectOne(OrdersDTO dto);

    int selectOrderNo(String member_id);

}
