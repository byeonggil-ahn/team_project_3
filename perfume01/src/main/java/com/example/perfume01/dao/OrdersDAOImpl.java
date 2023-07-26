package com.example.perfume01.dao;

import com.example.perfume01.dto.OrdersDTO;
import mapperInterface.OrdersMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

    OrdersMapper mapper;


    @Override
    public int insertOrder(OrdersDTO dto) {
        return mapper.insertOrder(dto);
    }

    @Override
    public List<OrdersDTO> selectList(OrdersDTO dto) {
        return mapper.selectList(dto);
    }

    @Override
    public OrdersDTO selectOne(OrdersDTO dto) {
        return mapper.selectOne(dto);
    }

    @Override
    public int selectOrderNo(String member_id) {
        return mapper.selectOrderNo(member_id);
    }
}
