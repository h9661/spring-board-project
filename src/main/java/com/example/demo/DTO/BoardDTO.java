package com.example.demo.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardDTO {
    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(max = 200)
    String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    String content;

    Integer userId;
}
