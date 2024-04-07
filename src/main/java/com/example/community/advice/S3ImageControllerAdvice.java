package com.example.community.advice;

import com.example.community.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import java.io.IOException;

@ControllerAdvice
public class S3ImageControllerAdvice {

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<MessageResponse> handleMultipartException(MultipartException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Failed to upload image: " + e.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<MessageResponse> handleIOException(IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Failed to process image: " + e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(e.getMessage()));
    }
}
