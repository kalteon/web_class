package com.example.community.domain;

import com.example.community.util.StringListConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @Convert(converter = StringListConverter.class)
    @Column(name = "imageNames")
    private List<String> imageNames;

    public Article(String title, String author, String content, List<String> imageNames) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.createAt = LocalDateTime.now();
        this.imageNames = imageNames;
    }

    public Article() {}

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

    public List<String> getImageNames() {return this.imageNames;}

    public void setId(Long id) {
        this.id = id;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
