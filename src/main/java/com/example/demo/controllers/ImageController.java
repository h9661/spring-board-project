package com.example.demo.controllers;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<ImageDTO> deleteImage(@PathVariable Integer id) {
        Image deletedImage = imageService.deleteById(id);
        ImageDTO imageDTO = new ImageDTO();

        imageDTO.setId(deletedImage.getId());
        imageDTO.setFilename(deletedImage.getFilename());
        imageDTO.setFilepath(deletedImage.getFilepath());

        return ResponseEntity.ok(imageDTO);
    }
}
