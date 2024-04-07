package com.example.community.dto;

import java.util.List;

public class CreateArticleRequest {
    private String title;
    private String author;
    private String content;
    private List<String> imageNames;

    public  CreateArticleRequest(String title, String author, String content, List<String> imageNames) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.imageNames = imageNames;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getImageNames() {
        return imageNames;
    }
}
