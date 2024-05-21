package CMS.Project.board.controller;

import CMS.Project.board.dto.board.BoardResponseDTO;
import CMS.Project.board.dto.board.BoardWriteRequestDTO;
import CMS.Project.board.dto.comment.CommentResponseDTO;
import CMS.Project.board.dto.image.BoardImageUploadDTO;
import CMS.Project.board.Service.board.BoardService;
import CMS.Project.board.Service.Comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }

    @PostMapping("/write")
    public String write(BoardWriteRequestDTO boardWriteRequestDTO,
                        @ModelAttribute BoardImageUploadDTO boardImageUploadDTO,
                        Authentication authentication) {

        logger.info("boardImageDTO is {}", boardImageUploadDTO);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boardService.saveBoard(boardWriteRequestDTO, boardImageUploadDTO, userDetails.getUsername());

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        BoardResponseDTO result = boardService.boardDetail(id);
        List<CommentResponseDTO> commentResponseDTO = commentService.commentList(id);

        model.addAttribute("comments", commentResponseDTO);
        model.addAttribute("dto", result);
        model.addAttribute("id", id);

        return "board/detail";
    }

    @GetMapping("/{id}/update")
    public String boardUpdateForm(@PathVariable Long id, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        BoardResponseDTO result = boardService.boardDetail(id);
        if (result.getEmail() != userDetails.getUsername()) {
            return "redirect:/";
        }

        model.addAttribute("dto", result);
        model.addAttribute("id", id);

        return "board/update";
    }


    @PostMapping("/{id}/update")
    public String boardUpdate(@PathVariable Long id, BoardWriteRequestDTO boardWriteRequestDTO) {
        boardService.boardUpdate(id, boardWriteRequestDTO);

        return "redirect:/board/" + id;
    }


    @GetMapping("/{id}/remove")
    public String boardRemove(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        BoardResponseDTO result = boardService.boardDetail(id);
        if (!Objects.equals(result.getEmail(), userDetails.getUsername())) {
            return "redirect:/";
        }

        boardService.boardRemove(id);

        return "redirect:/";
    }
}