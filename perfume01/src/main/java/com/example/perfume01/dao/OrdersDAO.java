package com.example.perfume01.dao;

import com.example.perfume01.dto.OrdersDTO;

import java.util.List;

public interface OrdersDAO {

    int insertOrder(OrdersDTO dto);

    List<OrdersDTO> selectList(OrdersDTO dto);

    OrdersDTO selectOne(OrdersDTO dto);

    int selectOrderNo(String member_id);

}
