package board.board.service;

import board.board.domain.Board;
import board.board.domain.BoardFile;
import board.board.paging.Criteria;
import board.board.repository.BoardMapper;
import board.common.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;
    private final FileUtils fileUtils;

    @Override
    public List<Board> selectBoardList(Criteria criteria) {
        return boardMapper.selectBoardList(criteria);
    }

    @Override
    public int getSize() {
        return boardMapper.getSize();
    }

    @Override
    public void insertBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        boardMapper.insertBoard(board);
        log.info("board={}", board);
        List<BoardFile> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
        if (CollectionUtils.isEmpty(list) == false) {
            boardMapper.insertFileList(list);
        }

        if (ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            String name;
            while (iterator.hasNext()) {
                name = iterator.next();
                log.debug("file tag name={}", name);
                List<MultipartFile> files = multipartHttpServletRequest.getFiles(name);
                for (MultipartFile multipartFile : files) {
                    log.debug("start file information");
                    log.debug("file name={}", multipartFile.getOriginalFilename());
                    log.debug("file size={}", multipartFile.getSize());
                    log.debug("file content type={}", multipartFile.getContentType());
                    log.debug("end file information.\n");
                }
            }
        }
    }

    @Override
    public Optional<Board> selectBoardDetail(int boardIdx) {
        Board board = boardMapper.selectBoardDetail(boardIdx).get();
        List<BoardFile> fileList = boardMapper.selectBoardFileList(boardIdx);
        board.setFileList(fileList);
        log.info("board={}", board);
        log.info("fileList={}", fileList);

        boardMapper.updateHitCount(boardIdx);

        return Optional.of(board);

    }

    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(int boardIdx) {
        boardMapper.deleteBoard(boardIdx);
    }

    @Override
    public Optional<BoardFile> selectBoardFileInformation(int idx, int boardIdx) {
        return Optional.of(boardMapper.selectBoardFileInformation(idx, boardIdx).get());
    }
}
