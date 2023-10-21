package com.example.demo.controllers;

import com.example.demo.DTO.BoardDTO;
import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String getWriteForm(BoardDTO boardDTO) {
        return "board/boardWrite";
    }

    @PostMapping("/write")
    public String writeBoard(@Valid BoardDTO boardDTO, BindingResult bindingResult, Principal principal)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return "board/boardWrite";
        }

        User user = userService.getUserByUsername(principal.getName());
        boardService.saveBoard(boardDTO, user);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String getBoardList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Board> boardList = boardService.getBoardList(page);
        model.addAttribute("paging", boardList);

        return "board/boardList";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Integer id, Model model, CommentDTO commentDTO) {
        model.addAttribute("board", boardService.getBoard(id));

        return "board/boardView";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable Integer id, Model model, Principal principal) {
        Board board = boardService.getBoard(id);
        if (!board.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setUserId(board.getUser().getId());

        model.addAttribute("boardDTO", boardDTO);

        return "board/boardWrite";
    }

    @PostMapping("/{id}/update")
    public String updateBoard(@PathVariable Integer id, @Valid BoardDTO board, BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return "board/boardWrite";
        }

        boardService.updateBoard(board, id);

        return "redirect:/board/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Integer id, Principal principal) {
        Board deletedBoard = boardService.deleteBoard(id);
        if (!deletedBoard.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/comment")
    public String writeComment(@PathVariable Integer id, Model model, @Valid CommentDTO commentDTO,
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            Board board = boardService.getBoard(id);
            model.addAttribute("board", board);
            return "board/boardView";
        }
        User user = userService.getUserByUsername(principal.getName());
        boardService.writeComment(id, commentDTO, user);
        return "redirect:/board/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Integer id, @PathVariable Integer commentId, Principal principal) {
        Board board = boardService.getBoard(id);
        if (!board.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        commentService.deleteComment(commentId);
        return "redirect:/board/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/comment/{commentId}/update")
    public String getUpdateCommentForm(@PathVariable Integer id, @PathVariable Integer commentId, Model model,
            Principal principal) {
        Board board = boardService.getBoard(id);
        if (!board.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        CommentDTO commentDTO = new CommentDTO();
        Comment comment = commentService.getComment(commentId);

        commentDTO.setContent(comment.getContent());
        model.addAttribute("commentDTO", commentDTO);

        return "board/commentUpdate";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable Integer id, @PathVariable Integer commentId,
            @Valid CommentDTO commentDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "board/commentUpdate";
        }

        commentService.updateComment(commentId, commentDTO);

        return "redirect:/board/" + id;
    }
}
