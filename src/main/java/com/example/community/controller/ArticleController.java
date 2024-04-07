package com.example.community.controller;

import com.example.community.dto.*;
import com.example.community.service.ArticleService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TitleDTO>> getTitles(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        List<TitleDTO> titleDTOList =  articleService.readTitles(page, pageSize);
        return ResponseEntity.ok().body(titleDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        ArticleDTO articleDTO =  articleService.readArticleById(id);
        return ResponseEntity.ok().body(articleDTO);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createArticle(@RequestBody CreateArticleRequest request) {
        articleService.createArticle(request);
        return ResponseEntity.ok().body(new MessageResponse("Article has been successfully created."));
    }

    @PatchMapping
    public ResponseEntity<MessageResponse> updateArticle(@RequestBody UpdateArticleRequest request) {
        articleService.updateArticleById(request);
        return ResponseEntity.ok().body(new MessageResponse("Article with ID " + request.getId() + " has been successfully updated."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return ResponseEntity.ok().body(new MessageResponse("Article with ID " + id + " has been successfully deleted."));
    }
}
