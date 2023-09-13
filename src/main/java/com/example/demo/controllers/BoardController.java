package com.example.demo.controllers;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String getWriteForm() {
        return "boardWrite";
    }

    @PostMapping("/write")
    public String newBoard(Board board) {
        boardService.writeBoard(board);
        return "redirect:/board/write";
    }
}
