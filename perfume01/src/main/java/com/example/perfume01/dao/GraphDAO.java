package com.example.perfume01.dao;

import com.example.perfume01.dto.GraphDTO;

import java.util.List;

public interface GraphDAO {
    List<GraphDTO> productGraph(GraphDTO dto);
}
