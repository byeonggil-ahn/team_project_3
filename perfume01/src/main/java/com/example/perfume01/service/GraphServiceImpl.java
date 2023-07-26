package com.example.perfume01.service;

import com.example.perfume01.dao.GraphDAO;
import com.example.perfume01.dto.GraphDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    GraphDAO dao;

    @Override
    public List<GraphDTO> productGraph(GraphDTO dto) {
        return dao.productGraph(dto);
    }
}
