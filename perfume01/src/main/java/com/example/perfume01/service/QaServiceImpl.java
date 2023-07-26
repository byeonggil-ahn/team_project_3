package com.example.perfume01.service;

import com.example.perfume01.dao.QaDAO;
import com.example.perfume01.dto.QaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QaServiceImpl implements QaService{

    @Autowired
    QaDAO dao;

    @Override
    public List<QaDTO> selectList() {
        return dao.selectList();
    }
}
