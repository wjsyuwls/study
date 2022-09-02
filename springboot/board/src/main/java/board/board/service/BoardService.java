package board.board.service;

import board.board.domain.Board;
import board.board.domain.BoardFile;
import board.board.paging.Criteria;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Board> selectBoardList(Criteria criteria);

    int getSize();

    void insertBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    Optional<Board> selectBoardDetail(int boardIdx);

    void updateBoard(Board board);

    void deleteBoard(int boardIdx);

    Optional<BoardFile> selectBoardFileInformation(int idx, int boardIdx);

}
