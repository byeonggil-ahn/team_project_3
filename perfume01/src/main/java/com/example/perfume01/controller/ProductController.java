package com.example.perfume01.controller;

import com.example.perfume01.dao.ProductDAO;
import com.example.perfume01.dao.ProductTagDAOImpl;
import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.dto.ProductTagDTO;
import com.example.perfume01.service.ProductService;
import com.example.perfume01.service.ProductTagService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@Log4j2
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    ProductDAO dao;

    ProductService service;

    ProductTagDAOImpl tagDao;

    ProductTagService tagService;
    @GetMapping("/list")
    public List<ProductDTO> productList() {
        List<ProductDTO> rtn = service.selectList();
        return rtn;
    }

    @GetMapping("/detail")
    public ProductDTO productDetail(ProductDTO dto) {
        int pno = dto.getProduct_no();
        ProductDTO pd = service.selectOne(dto);
        System.out.println(pd);
        return pd;
    }

}
