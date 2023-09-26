package com.example.demo.controllers;

import com.example.demo.DTO.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String writeBoard(BoardDTO board, Model model) throws Exception {
        Board savedBoard = boardService.saveBoard(board);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("url", "/board/list");

        return "board/message";
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

    @PostMapping("/{id}/comment")
    @ResponseBody
    public ResponseEntity<String> writeComment(@PathVariable Integer id, @RequestBody String content) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(content, JsonElement.class);
        // todo: service에서 처리하도록 수정

        return ResponseEntity.ok(jsonElement.toString());
    }
}
