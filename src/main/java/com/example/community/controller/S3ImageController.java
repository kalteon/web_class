package com.example.community.controller;

import com.example.community.dto.FileNameResponse;
import com.example.community.dto.MessageResponse;
import com.example.community.service.S3ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class S3ImageController {

    private final S3ImageService s3ImageService;

    public S3ImageController(S3ImageService s3ImageService) {
        this.s3ImageService = s3ImageService;
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        Resource imageResource = s3ImageService.getImage(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok().body(imageResource);
    }

    @PostMapping
    public ResponseEntity<FileNameResponse> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        String imageName = s3ImageService.uploadImage(imageFile);
        System.out.printf(imageName);
        return ResponseEntity.ok().body(new FileNameResponse(imageName));
    }

    @DeleteMapping("/{imageName}")
    public ResponseEntity<MessageResponse> deleteImage(@PathVariable String imageName) {
        s3ImageService.deleteImage(imageName);
        return ResponseEntity.ok().body(new MessageResponse("Image " + imageName + " has been deleted successfully."));
    }
}
