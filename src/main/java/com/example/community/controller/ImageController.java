package com.example.community.controller;

import com.example.community.dto.FileNameResponse;
import com.example.community.dto.MessageResponse;
import com.example.community.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{imageName}")
    public Resource readImage(@PathVariable String imageName) throws IOException {
        Resource imageResource = imageService.getImage(imageName);
        return imageResource;
    }

    @PostMapping
    public FileNameResponse uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        String fileName = imageService.uploadImage(imageFile);
        return new FileNameResponse(fileName);
    }

    @DeleteMapping("/{imageName}")
    public MessageResponse deleteImage(@PathVariable String imageName) throws IOException {
        imageService.deleteImage(imageName);
        return new MessageResponse("Image " + imageName + " has been deleted successfully.");
    }
}