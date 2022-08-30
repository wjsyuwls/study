package board.board.service;

import board.board.dto.BoardDTO;
import board.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> selectBoardList() {
        return boardMapper.selectBoardList();
    }

    @Override
    public void insertBoard(BoardDTO boardDTO) {
        boardMapper.insertBoard(boardDTO);
    }

    @Override
    public Optional<BoardDTO> selectBoardDetail(int boardIdx) {
        boardMapper.updateHitCount(boardIdx);

        return boardMapper.selectBoardDetail(boardIdx);
    }
}
