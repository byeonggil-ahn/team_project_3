package com.example.perfume01.service;

import com.example.perfume01.dao.BoardDAO;
import com.example.perfume01.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO dao;

    @Override
    public List<BoardDTO> boardList() {
        return dao.boardList();
    }

    @Override
    public void insertQa(BoardDTO data) {
        System.out.println("디티오 위에"+data);
        BoardDTO dto = new BoardDTO();

        dto.setMember_id(data.getMember_id());
        dto.setQa_title(data.getQa_title());
        dto.setQa_content(data.getQa_content());
        dto.setQa_answer("입력x");
        dto.setQa_head(data.getQa_title());
        dto.setQa_secret(data.getQa_secret());
        dto.setQa_regdate(data.getQa_regdate());
        System.out.println("DTO아래"+dto.toString());
        dao.insertQa(dto);
    }
}
