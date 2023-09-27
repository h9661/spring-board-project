package com.example.demo.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentDTO {
  @NotEmpty(message = "내용을 입력해주세요.")
  String content;
  BoardDTO boardDTO;
  LocalDateTime createdAt;
}
