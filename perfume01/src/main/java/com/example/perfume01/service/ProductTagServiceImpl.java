package com.example.perfume01.service;

import com.example.perfume01.dao.ProductTagDAO;
import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.dto.ProductTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTagServiceImpl implements ProductTagService{

    @Autowired
    ProductTagDAO dao;

    // RESTAPI 적용 해볼수 있을만한 쿼리문 @Param
    @Override
    public ProductDTO selectOne(ProductDTO dto) {
        return dao.selectOne(dto);
    }

    @Override
    public int insertTag(ProductDTO dto) {
        return dao.insertTag(dto);
    }

    @Override
    public int editTag(ProductDTO dto) {
        return dao.editTag(dto);
    }

    @Override
    public int deleteTag(ProductDTO dto) {
        return dao.deleteTag(dto);
    }


}
