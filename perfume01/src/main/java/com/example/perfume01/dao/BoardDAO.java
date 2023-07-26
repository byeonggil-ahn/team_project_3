package com.example.perfume01.dao;

import com.example.perfume01.dto.BoardDTO;

import java.util.List;

public interface BoardDAO {

    List<BoardDTO> boardList();

    void insertQa(BoardDTO dto);

}
