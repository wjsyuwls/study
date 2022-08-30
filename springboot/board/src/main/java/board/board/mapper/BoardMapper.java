package board.board.mapper;

import board.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<BoardDTO> selectBoardList();

    void insertBoard(BoardDTO boardDTO);

    void updateHitCount(int boardIdx);

    Optional<BoardDTO> selectBoardDetail(int boardIdx);
}
