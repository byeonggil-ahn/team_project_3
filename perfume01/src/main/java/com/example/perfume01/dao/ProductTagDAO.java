package com.example.perfume01.dao;

import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.dto.ProductTagDTO;

import java.util.List;

public interface ProductTagDAO {

    int insertTag(ProductDTO dto);

    int editTag(ProductDTO dto);

    int deleteTag(ProductDTO dto);

    ProductDTO selectOne(ProductDTO dto);



}
