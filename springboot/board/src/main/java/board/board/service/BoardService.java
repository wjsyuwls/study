package board.board.service;

import board.board.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<BoardDTO> selectBoardList();

    void insertBoard(BoardDTO boardDTO);

    Optional<BoardDTO> selectBoardDetail(int boardIdx);
}
