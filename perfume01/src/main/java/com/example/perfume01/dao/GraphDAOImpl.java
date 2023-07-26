package com.example.perfume01.dao;

import com.example.perfume01.dto.GraphDTO;
import mapperInterface.GraphMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GraphDAOImpl implements GraphDAO {

    @Autowired
    GraphMapper mapper;

    @Override
    public List<GraphDTO> productGraph(GraphDTO dto) {
        return mapper.productGraph(dto);
    }
}
