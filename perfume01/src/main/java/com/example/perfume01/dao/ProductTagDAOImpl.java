package com.example.perfume01.dao;

import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.dto.ProductTagDTO;
import mapperInterface.ProductTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductTagDAOImpl implements ProductTagDAO{

    @Autowired
    ProductTagMapper mapper;

    @Override
    public int insertTag(ProductDTO dto) {
        return mapper.insertTag(dto);
    }

    @Override
    public int editTag(ProductDTO dto) {
        return mapper.editTag(dto);
    }

    @Override
    public int deleteTag(ProductDTO dto) {
        return mapper.deleteTag(dto);
    }


    @Override
    public ProductDTO selectOne(ProductDTO dto) {
        return mapper.selectOne(dto);
    }


}
