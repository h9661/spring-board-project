package com.example.demo.controllers;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String getWriteForm() {
        return "board/boardWrite";
    }

    @PostMapping("/write")
    public String newBoard(Board board) {
        boardService.writeBoard(board);
        return "redirect:/board/write";
    }

    @GetMapping("/list")
    public String getBoardList(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());

        return "board/boardList";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Integer id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));

        return "board/boardView";
    }

    @PostMapping("/{id}")
    public String updateBoard(@PathVariable Integer id, Board board) {
        Board updatedBoard = boardService.updateBoard(id, board);

        System.out.println(updatedBoard.toString());

        return "redirect:/board/list";
    }
}
