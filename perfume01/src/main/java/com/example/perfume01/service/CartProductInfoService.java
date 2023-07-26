package com.example.perfume01.service;

import com.example.perfume01.dto.CartProductInfoDTO;

import java.util.List;

public interface CartProductInfoService {

    public List<CartProductInfoDTO> cartInfoList(String member_id);

}
