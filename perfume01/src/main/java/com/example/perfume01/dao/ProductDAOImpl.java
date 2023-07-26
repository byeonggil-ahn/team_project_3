package com.example.perfume01.dao;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dto.ProductDTO;
import mapperInterface.ProductManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    ProductManageMapper mapper;

    @Override
    public List<ProductDTO> selectList() {
        return mapper.selectList();
    }

    @Override
    public ProductDTO selectOne(ProductDTO dto) {
        return mapper.selectOne(dto);
    }

    @Override
    public int selectStock(int product_no) {
        return mapper.selectStock(product_no);
    }

    @Override
    public int insertProduct(ProductDTO dto) {
        return mapper.insertProduct(dto);
    }

    @Override
    public int edit(ProductDTO dto) {
        return mapper.edit(dto);
    }

    @Override
    public int deleteProduct(ProductDTO dto) {
        return mapper.deleteProduct(dto);
    }

    @Override
    public int deleteEachProduct(ProductDTO dto) {
        return mapper.deleteEachProduct(dto);
    }

    @Override
    public List<ProductDTO> searchList(SearchCriteria criteria) {
        return mapper.searchList(criteria);
    }

    @Override
    public int searchTotalCount(SearchCriteria criteria) {
        return mapper.searchTotalCount(criteria);
    }


}
