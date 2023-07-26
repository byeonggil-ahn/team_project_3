package com.example.perfume01.service;

import com.example.perfume01.dao.CartDAO;
import com.example.perfume01.dao.CartProductInfoDAO;
import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.CartProductInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDAO dao;

    //CartProductInfoDAO infoDAO;


    @Override
    public int cartInsert(CartDTO dto) {
        return dao.cartInsert(dto);
    }

    @Override
    public CartDTO selectOne(CartDTO dto) {
        return dao.selectOne(dto);
    }

    @Override
    public List<CartDTO> cartList(String member_id) {
        return dao.cartList(member_id);
    }


    @Override
    public int cartChangeCount(int product_count, int product_no, String member_id) {
        CartDTO dto = new CartDTO();
        dto.setProduct_count(product_count);
        dto.setProduct_no(product_no);
        dto.setMember_id(member_id);

        return dao.cartChangeCount(dto);
    }


    @Override
    public int cartDeleteItem(CartDTO dto) {
        return dao.cartDeleteItem(dto);
    }

    @Override
    public int cartDeleteAll(CartDTO dto) {
        return dao.cartDeleteAll(dto);
    }

    @Override
    public int cartImg(CartProductInfoDTO dto) {
        return dao.cartImg(dto);
    }

    @Override
    public int cartCnt(String member_id) {
        return dao.cartCnt(member_id);
    }


}
