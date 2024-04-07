package com.example.community.controller;

import com.example.community.dto.*;
import com.example.community.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/titles")
    public List<TitleDTO> getTitles(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return articleService.readTitles(page, pageSize);
    }

    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable Long id) {
        return articleService.readArticleById(id);
    }

    @PostMapping
    public MessageResponse createArticle(@RequestBody CreateArticleRequest request) {
        articleService.createArticle(request);
        return new MessageResponse("Article has been successfully created.");
    }

    @PatchMapping
    public MessageResponse updateArticle(@RequestBody UpdateArticleRequest request) {
        articleService.updateArticleById(request);
        return new MessageResponse("Article with ID " + request.getId() + " has been successfully updated.");
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return new MessageResponse("Article with ID " + id + " has been successfully deleted.");
    }
}
