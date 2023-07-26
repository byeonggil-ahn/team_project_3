package com.example.perfume01.dao;

import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.CartProductInfoDTO;

import java.util.List;

public interface CartDAO {

    int cartInsert(CartDTO dto);

    CartDTO selectOne(CartDTO dto);

    List<CartDTO> cartList(String member_id);

    int cartChangeCount(CartDTO dto);

    int cartDeleteItem(CartDTO dto);

    int cartDeleteAll(CartDTO dto);

    int cartImg(CartProductInfoDTO dto);

    int cartCnt(String member_id);



}
