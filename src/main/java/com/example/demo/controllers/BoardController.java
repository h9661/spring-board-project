package com.example.demo.controllers;

import com.example.demo.DTO.BoardDTO;
import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

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

    @PostMapping("/{id}/update")
    public String updateBoard(@PathVariable Integer id, BoardDTO board) throws Exception {
        boardService.updateBoard(board, id);

        return "redirect:/board/list";
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<Board> deleteBoard(@PathVariable Integer id) {
        Board deletedBoard = boardService.deleteBoard(id);

        return ResponseEntity.ok(deletedBoard);
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
}
