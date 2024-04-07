package com.example.community.dto;

import java.time.LocalDateTime;
import java.util.List;


public class ArticleDTO {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createAt;
    private List<String> imageNames;

    public ArticleDTO(Long id, String title, String author, String content, LocalDateTime createAt, List<String> imageNames) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createAt = createAt;
        this.imageNames = imageNames;
    }

    public Long getId() {
        return this.id;
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

    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    public List<String> getImageNames() {
        return imageNames;
    }
}
