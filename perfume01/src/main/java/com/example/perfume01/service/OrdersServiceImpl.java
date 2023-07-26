package com.example.perfume01.service;

import com.example.perfume01.dao.OrdersDAO;
import com.example.perfume01.dao.OrdersDAOImpl;
import com.example.perfume01.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDAO dao;

    @Override
    public int insertOrder(OrdersDTO dto) {
        return dao.insertOrder(dto);
    }

    @Override
    public List<OrdersDTO> selectList(OrdersDTO dto) {
        return dao.selectList(dto);
    }

    @Override
    public OrdersDTO selectOne(OrdersDTO dto) {
        return dao.selectOne(dto);
    }

    @Override
    public int selectOrderNo(String member_id) {
        return dao.selectOrderNo(member_id);
    }
}
