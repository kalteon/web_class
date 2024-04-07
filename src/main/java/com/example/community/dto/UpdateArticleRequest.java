package com.example.community.dto;

import java.util.List;

public class UpdateArticleRequest {
    private Long id;
    private String title;
    private String content;
    public  UpdateArticleRequest(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
