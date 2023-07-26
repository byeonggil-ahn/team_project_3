package com.example.perfume01.controller;

import com.example.perfume01.dto.BoardDTO;
import com.example.perfume01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService service;

    @GetMapping("/all")
    public List<BoardDTO> boardList() {
        List<BoardDTO> rtn = service.boardList();
        return rtn;
    }

    @PostMapping("/addqa")
    public void qaInsert(@RequestBody BoardDTO dto) {
        service.insertQa(dto);
    }
}
