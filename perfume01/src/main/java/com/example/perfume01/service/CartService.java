package com.example.perfume01.service;

import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.CartProductInfoDTO;

import java.util.List;

public interface CartService {

    int cartInsert(CartDTO dto);

    CartDTO selectOne(CartDTO dto);

    List<CartDTO> cartList(String member_id);

//    int cartChangeCount(CartDTO dto);
    int cartChangeCount(int product_count, int product_no, String member_id);

    int cartDeleteItem(CartDTO dto);

    int cartDeleteAll(CartDTO dto);

    int cartImg(CartProductInfoDTO dto);

    int cartCnt(String member_id);

    //List<CartProductInfoDTO> cartInfoList(String member_id);
}
