package com.example.perfume01.dao;

import com.example.perfume01.dto.QaDTO;
import mapperInterface.QaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QaDAOImpl implements QaDAO {

    @Autowired
    QaMapper mapper;

    @Override
    public List<QaDTO> selectList() {
        return mapper.selectList();
    }
}
