package com.example.perfume01.service;

import com.example.perfume01.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> boardList();

    void insertQa(BoardDTO dto);

}
