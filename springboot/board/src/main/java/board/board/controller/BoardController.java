package board.board.controller;

import board.board.dto.BoardDTO;
import board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/openBoardList")
    public String openBoardList(Model model) {
        List<BoardDTO> list = boardService.selectBoardList();
        model.addAttribute("list", list);
        log.info("list={}", list);

        return "board/boardList";
    }

    @GetMapping("/openBoardWrite")
    public String openBoardWrite() {
        return "board/boardWrite";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(@ModelAttribute BoardDTO boardDTO) {
        boardService.insertBoard(boardDTO);
        return "redirect:/board/openBoardList";
    }

    @GetMapping("/openBoardDetail")
    public String openBoardDetail(@RequestParam int boardIdx, Model model) {
        log.info("boardIdx={}", boardIdx);
        BoardDTO board = boardService.selectBoardDetail(boardIdx).get();
        log.info("board={}", board);
        model.addAttribute("board", board);
        return "board/boardDetail";
    }
}
