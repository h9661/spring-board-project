package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BoardDTO {
    Integer id;
    String title;
    String content;

    List<ImageDTO> images;
}
