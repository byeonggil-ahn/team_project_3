package com.example.perfume01.dao;

import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.CartProductInfoDTO;
import mapperInterface.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    @Autowired
    CartMapper mapper;

    @Override
    public int cartInsert(CartDTO dto) {
        return mapper.cartInsert(dto);
    }

    @Override
    public CartDTO selectOne(CartDTO dto) {
        return mapper.selectOne(dto);
    }

    @Override
    public List<CartDTO> cartList(String member_id) {
        return mapper.cartList(member_id);
    }

    @Override
    public int cartChangeCount(CartDTO dto) {
        return mapper.cartChangeCount(dto.getProduct_count(), dto.getProduct_no(), dto.getMember_id());
    }


    @Override
    public int cartDeleteItem(CartDTO dto) {
        return mapper.cartDeleteItem(dto);
    }

    @Override
    public int cartDeleteAll(CartDTO dto) {
        return mapper.cartDeleteAll(dto);
    }

    @Override
    public int cartImg(CartProductInfoDTO dto) {
        return mapper.cartImg(dto);
    }

    @Override
    public int cartCnt(String member_id) {
        return mapper.cartCnt(member_id);
    }

}
