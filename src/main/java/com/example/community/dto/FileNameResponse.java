package com.example.community.dto;

public class FileNameResponse {

    private String fileName;

    public FileNameResponse(String message) {
        this.fileName = message;
    }

    public String getFileName() {
        return this.fileName;
    }
}
