package com.example.perfume01.controller;

import com.example.perfume01.dto.GraphDTO;
import com.example.perfume01.service.GraphService;
import mapperInterface.GraphMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class GraphController {

    @Autowired
    GraphService service;

    @GetMapping("/canvas")
    public List<GraphDTO> productGraph(Model model, GraphDTO dto) {
        List<GraphDTO> graph = service.productGraph(dto);

        return graph;
    }


}
