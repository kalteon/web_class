package com.example.community.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    public Resource getImage(String imageName) throws IOException {
        try {
            Path imageFile = getImagePath(imageName);
            Resource resource = new UrlResource(imageFile.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new IOException("Could not read the image");
            }
        } catch (MalformedURLException e) {
            throw new IOException("Malformed URL: " + e.getMessage());
        }
    }

    public String uploadImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Invalid image file: " + imageFile.getOriginalFilename());
        }
        String fileName = generateFileName(imageFile.getOriginalFilename());
        String filePath = uploadPath + File.separator + fileName;
        try {
            FileCopyUtils.copy(imageFile.getInputStream(), new FileOutputStream(filePath));
            return fileName;
        } catch (IOException e) {
            throw new IOException("Failed to upload image: " + e.getMessage());
        }
    }

    public void deleteImage(String imageName) throws IOException {
        Path imagePath = getImagePath(imageName);
        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            throw new IOException("Failed to delete image: " + e.getMessage());
        }
    }

    private Path getImagePath(String imageName) {
        return Paths.get(uploadPath).resolve(imageName);
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }
}
