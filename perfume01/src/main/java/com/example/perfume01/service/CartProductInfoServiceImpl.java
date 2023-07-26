package com.example.perfume01.service;

import com.example.perfume01.dao.CartProductInfoDAO;
import com.example.perfume01.dto.CartProductInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductInfoServiceImpl implements CartProductInfoService {

    @Autowired
    CartProductInfoDAO dao;

    @Override
    public List<CartProductInfoDTO> cartInfoList(String member_id) {
        //return dao.cartInfoList(member_id);
        return null;
    }
}
