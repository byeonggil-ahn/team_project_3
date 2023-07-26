package com.example.perfume01.dao;

import com.example.perfume01.dto.ProductCategoryStateDTO;
import mapperInterface.ProductCategoryStateMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCategoryStateDAO {

    private final SqlSession sqlSession;
    private final ProductCategoryStateMapper productCategoryStatMapper;

    @Autowired
    public ProductCategoryStateDAO(SqlSession sqlSession, ProductCategoryStateMapper productCategoryStatMapper) {
        this.sqlSession = sqlSession;
        this.productCategoryStatMapper = productCategoryStatMapper;
    }

    public List<ProductCategoryStateDTO> list() {
        return productCategoryStatMapper.list();
    }

}
