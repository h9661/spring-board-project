package com.example.demo.DTO;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageFiles {
    private List<MultipartFile> imageFiles;
}
