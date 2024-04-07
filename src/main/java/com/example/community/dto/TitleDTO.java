package com.example.community.dto;

import java.time.LocalDateTime;

public class TitleDTO {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createAt;


    public TitleDTO(Long id, String title, String author, LocalDateTime createAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createAt = createAt;
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

    public LocalDateTime getCreateAt() {
        return this.createAt;
    }
}
