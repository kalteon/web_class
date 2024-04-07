package com.example.community.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3ImageService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public S3ImageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public Resource getImage(String imageName) throws IOException {
        try {
            S3Object s3Object = amazonS3.getObject(bucketName, imageName);
            InputStream inputStream = s3Object.getObjectContent();
            byte[] imageData = inputStream.readAllBytes();
            return new ByteArrayResource(imageData);
        } catch (Exception e) {
            throw new IOException("Failed to read the image from AWS S3: " + e.getMessage());
        }
    }

    public String uploadImage(MultipartFile imageFile) throws IOException {
        System.out.printf("service in");
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Invalid image file: " + imageFile.getOriginalFilename());
        }

        String fileName = generateFileName(imageFile.getOriginalFilename());

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(imageFile.getContentType());
            metadata.setContentLength(imageFile.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, imageFile.getInputStream(), metadata);
            amazonS3.putObject(putObjectRequest);
        } catch (IOException e) {
            throw new IOException("Failed to upload image to AWS S3: " + e.getMessage());
        }
        System.out.printf("service out");
        return fileName;
    }

    public void deleteImage(String imageName) {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, imageName));
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete image from AWS S3: " + e.getMessage());
        }
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }
}
