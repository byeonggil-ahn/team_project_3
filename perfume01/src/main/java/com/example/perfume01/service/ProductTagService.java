package com.example.perfume01.service;

import com.example.perfume01.dto.ProductDTO;
//import com.example.perfume01.dto.ProductTagDTO;

public interface ProductTagService {

    ProductDTO selectOne(ProductDTO dto);

    int insertTag(ProductDTO dto);

    int editTag(ProductDTO dto);

    int deleteTag(ProductDTO dto);
}
