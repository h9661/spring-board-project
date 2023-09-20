package com.example.demo.controllers;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/{id]/delete")
    @ResponseBody
    public ResponseEntity<Image> deleteImage(Integer id) {
        Image deletedImage = imageService.deleteById(id);

        return ResponseEntity.ok(deletedImage);
    }
}
