package board.board.repository;

import board.board.domain.Board;
import board.board.domain.BoardFile;
import board.board.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<Board> selectBoardList(Criteria criteria);

    int getSize();

    void insertBoard(Board board);

    void updateHitCount(int boardIdx);

    Optional<Board> selectBoardDetail(int boardIdx);

    void updateBoard(Board board);

    void deleteBoard(int boardIdx);

    void insertFileList(List<BoardFile> list);

    List<BoardFile> selectBoardFileList(int boardIdx);

    Optional<BoardFile> selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx") int boardIdx);
}
