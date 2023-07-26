package com.example.perfume01.dao;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {

    List<ProductDTO> selectList();

    ProductDTO selectOne(ProductDTO dto);

    int selectStock(int product_no);

    int insertProduct(ProductDTO dto);

    int edit(ProductDTO dto);

    int deleteProduct(ProductDTO dto);

    int deleteEachProduct(ProductDTO dto);

    List<ProductDTO> searchList(SearchCriteria criteria);

    int searchTotalCount(SearchCriteria criteria);
}
