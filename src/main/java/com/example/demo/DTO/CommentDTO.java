package com.example.demo.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
  Integer id;
  String content;
  BoardDTO boardDTO;
  LocalDateTime createdAt;
}
