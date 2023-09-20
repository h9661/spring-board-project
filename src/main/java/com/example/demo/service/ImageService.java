package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(Board board, MultipartFile imageFile) throws Exception {
        Image image = new Image();
        System.out.println(board.getId());

        String savingPath = System.getProperty("user.dir") + "/files/";
        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        File dest = new File(savingPath + fileName);

        imageFile.transferTo(dest);
        image.setFilename(fileName);
        image.setFilepath("/files/" + fileName);
        image.setBoard(board);

        return imageRepository.save(image);
    }
}
