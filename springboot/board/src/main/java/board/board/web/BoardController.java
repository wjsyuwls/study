package board.board.web;

import board.board.domain.Board;
import board.board.domain.BoardFile;
import board.board.paging.Criteria;
import board.board.paging.PageDTO;
import board.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;

@Api(description = "게시판 RESTFUL")
@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 목록 조회")
    @GetMapping
    public String openBoardList(Model model, @ModelAttribute Criteria criteria) {
        List<Board> list = boardService.selectBoardList(criteria);
        PageDTO pageMaker = new PageDTO(3, boardService.getSize(), criteria);
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", pageMaker);

        log.info("list={}", list);
        log.info("pageMaker={}", pageMaker);

        return "board/boardList";
    }

    @ApiOperation(value = "게시글 작성")
    @GetMapping("/write")
    public String openBoardWrite() {
        return "board/boardWrite";
    }

    @ApiOperation(value = "게시글 작성")
    @PostMapping("/write")
    public String insertBoard(@ModelAttribute Board board, MultipartHttpServletRequest multipartHttpServletRequest, RedirectAttributes redirectAttributes) throws Exception {
        boardService.insertBoard(board, multipartHttpServletRequest);
        redirectAttributes.addAttribute("boardIdx", board.getBoardIdx());
        return "redirect:/board/{boardIdx}";
    }

    @ApiOperation(value = "게시글 상세 내용 조회")
    @GetMapping("/{boardIdx}")
    public String openBoardDetail(@PathVariable int boardIdx, Model model) {
        Board board = boardService.selectBoardDetail(boardIdx).get();
        log.info("board={}", board);
        model.addAttribute("board", board);
        return "board/boardDetail";
    }

    @ApiOperation(value = "게시글 상세 내용 수정")
    @PutMapping("/{boardIdx}")
    public String updateBoard(@ModelAttribute Board board) {
        boardService.updateBoard(board);
        return "redirect:/board";
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/{boardIdx}")
    public String deleteBoard(@PathVariable int boardIdx) {
        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }

    @ApiOperation(value = "게시글 상세 첨부 파일 다운로드")
    @GetMapping("/board/file/{idx}/{boardIdx}")
    public void downloadBoardFile(@PathVariable int idx, @PathVariable int boardIdx, HttpServletResponse response) throws Exception {
        BoardFile boardFile = boardService.selectBoardFileInformation(idx, boardIdx).get();
        if (ObjectUtils.isEmpty(boardFile) == false) {
            String fileName = boardFile.getOriginalFileName();

            byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));

            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}
