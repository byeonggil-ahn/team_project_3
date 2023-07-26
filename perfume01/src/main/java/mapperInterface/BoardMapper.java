package mapperInterface;

import com.example.perfume01.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {

    List<BoardDTO> boardList();

    void insertQa(BoardDTO dto);

}
