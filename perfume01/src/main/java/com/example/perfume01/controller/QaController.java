package com.example.perfume01.controller;

import com.example.perfume01.dto.QaDTO;
import com.example.perfume01.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class QaController {

    @Autowired
    QaService service;

    @GetMapping("/list")
    public List<QaDTO> selectList() {
        List<QaDTO> rtn = service.selectList();
        return rtn;
    }

}
