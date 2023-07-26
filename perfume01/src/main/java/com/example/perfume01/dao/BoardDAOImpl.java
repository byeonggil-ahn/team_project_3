package com.example.perfume01.dao;

import com.example.perfume01.dto.BoardDTO;
import mapperInterface.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    BoardMapper mapper;

    @Override
    public List<BoardDTO> boardList() {
        return mapper.boardList();
    }

    @Override
    public void insertQa(BoardDTO dto) {
        mapper.insertQa(dto);
    }
}
