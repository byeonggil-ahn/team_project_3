package com.example.perfume01.controller;

import com.example.perfume01.dao.ProductCategoryStateDAO;
import com.example.perfume01.dto.ProductCategoryStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/productManage")
public class PmRestController {

    @Autowired
    private ProductCategoryStateDAO productCategoryStateDAO;

    // 카테고리별 상품 개수
    @GetMapping("/categoryCount")
    public Map<String, List<Object>> categoryCount() {
        List<ProductCategoryStateDTO> list = productCategoryStateDAO.list();
        List<Object> tags = new ArrayList<>();
        List<Object> cnts = new ArrayList<>();
        for (ProductCategoryStateDTO dto : list) {
            tags.add(dto.getTagNo());
            cnts.add(dto.getProductCnt());
        }
        Map<String, List<Object>> map = new HashMap<>();
        map.put("label", tags);
        map.put("cnts", cnts);
        return map;
    }

    // 카테고리별 판매량 평균, 최대값
    @GetMapping("/categorySellCount")
    public Map<String, List<Object>> categorySellCount() {
        List<ProductCategoryStateDTO> list = productCategoryStateDAO.list();
        List<Object> tags = new ArrayList<>();
        List<Object> avges = new ArrayList<>();
        List<Object> maxs = new ArrayList<>();
        list.forEach(dto -> {
            tags.add(dto.getTagNo());
            avges.add(dto.getSellAvg());
            maxs.add(dto.getSellMax());
        });
        Map<String, List<Object>> map = new HashMap<>();
        map.put("label", tags);
        map.put("avges", avges);
        map.put("maxs", maxs);
        return map;
    }

    // 카테고리별 가격 평균, 최대값, 최소값
    @GetMapping("/categoryPrice")
    public Map<String, List<Object>> categoryPrice() {
        List<ProductCategoryStateDTO> list = productCategoryStateDAO.list();
        List<Object> tags = new ArrayList<>();
        List<Object> avges = new ArrayList<>();
        List<Object> maxs = new ArrayList<>();
        List<Object> mins = new ArrayList<>();
        list.forEach(dto -> {
            tags.add(dto.getTagNo());
            avges.add(dto.getPriceAvg());
            maxs.add(dto.getPriceMax());
            mins.add(dto.getPriceMin());
        });
        Map<String, List<Object>> map = new HashMap<>();
        map.put("label", tags);
        map.put("avges", avges);
        map.put("maxs", maxs);
        map.put("mins", mins);
        return map;
    }

}
