package com.example.perfume01.service;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dao.ProductDAO;
import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDAO dao;

    @Override
    public List<ProductDTO> selectList() {
        return dao.selectList();
    }

    @Override
    public ProductDTO selectOne(ProductDTO dto) {
        return dao.selectOne(dto);
    }

    @Override
    public int selectStock(int product_no) {
        return dao.selectStock(product_no);
    }

    @Override
    public int edit(ProductDTO dto) {
        return dao.edit(dto);
    }

    @Override
    public int insertProduct(ProductDTO dto) {
        return dao.insertProduct(dto);
    }

    @Override
    public int deleteProduct(ProductDTO dto) {
        return dao.deleteProduct(dto);
    }

    @Override
    public int deleteEachProduct(ProductDTO dto) {
        return dao.deleteEachProduct(dto);
    }

    @Override
    public List<ProductDTO> searchList(SearchCriteria criteria) {
        return dao.searchList(criteria);
    }

    @Override
    public int searchTotalCount(SearchCriteria criteria) {
        return dao.searchTotalCount(criteria);
    }

}
