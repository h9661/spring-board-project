package com.example.demo.DTO;

import lombok.Data;


@Data
public class ImageDTO {
    Integer id;
    String filename;
    String filepath;
    BoardDTO boardDTO;
}
