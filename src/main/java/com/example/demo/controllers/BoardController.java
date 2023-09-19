package com.example.demo.controllers;

import com.example.demo.DTO.ImageFiles;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String writeBoard(Board board, @ModelAttribute ImageFiles imageFiles, Model model) throws Exception{
        imageFiles.getImageFiles().forEach(imageFile -> {
            System.out.println(imageFile.getOriginalFilename());
        });

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
    public String updateBoard(@PathVariable Integer id, Board board, MultipartFile file) throws Exception {
        Board boardToUpdate = boardService.getBoard(id);
        boardToUpdate.setTitle(board.getTitle());
        boardToUpdate.setContent(board.getContent());

        // todo: boardService.writeBoard 수정하기
        // boardService.writeBoard(boardToUpdate, file);

        return "redirect:/board/list";
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<Board> deleteBoard(@PathVariable Integer id) {
        Board deletedBoard = boardService.deleteBoard(id);

        return ResponseEntity.ok(deletedBoard);
    }
}
