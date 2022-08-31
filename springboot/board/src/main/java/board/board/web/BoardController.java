package board.board.web;

import board.board.domain.Board;
import board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/openBoardList")
    public String openBoardList(Model model) {
        List<Board> list = boardService.selectBoardList();
        model.addAttribute("list", list);
        log.info("list={}", list);

        return "board/boardList";
    }

    @GetMapping("/openBoardWrite")
    public String openBoardWrite() {
        return "board/boardWrite";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(@ModelAttribute Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        boardService.insertBoard(board, multipartHttpServletRequest);
        return "redirect:/board/openBoardList";
    }

    @GetMapping("/openBoardDetail")
    public String openBoardDetail(@RequestParam int boardIdx, Model model) {
        log.info("boardIdx={}", boardIdx);
        Board board = boardService.selectBoardDetail(boardIdx).get();
        log.info("board={}", board);
        model.addAttribute("board", board);
        return "board/boardDetail";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(@ModelAttribute Board board) {
        boardService.updateBoard(board);
        return "redirect:/board/openBoardList";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(int boardIdx) {
        boardService.deleteBoard(boardIdx);
        return "redirect:/board/openBoardList";
    }
}
