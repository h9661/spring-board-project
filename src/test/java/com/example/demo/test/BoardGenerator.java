package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.DTO.BoardDTO;
import com.example.demo.service.BoardService;

@SpringBootTest
public class BoardGenerator {

  @Autowired
  BoardService boardService;

  @Test
  public void generate() {
    for (int i = 0; i < 300; i++) {
      BoardDTO board = new BoardDTO();
      board.setTitle("제목" + i);
      board.setContent("내용" + i);
      boardService.saveBoard(board);
    }
  }
}
