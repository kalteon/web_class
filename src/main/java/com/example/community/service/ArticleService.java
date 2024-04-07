package com.example.community.service;

import com.example.community.domain.Article;
import com.example.community.dto.ArticleDTO;
import com.example.community.dto.CreateArticleRequest;
import com.example.community.dto.TitleDTO;
import com.example.community.dto.UpdateArticleRequest;
import com.example.community.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<TitleDTO> readTitles(int page, int pageSize) {
        return createTitleDTOList(page, pageSize);
    }

    public ArticleDTO readArticleById(Long id) {
        return articleRepository.findById(id)
                .map(this::convertToArticleDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with id " + id + " not found."));
    }

    public void createArticle(CreateArticleRequest request) {
        try {
            Article article = new Article(
                    request.getTitle(),
                    request.getAuthor(),
                    request.getContent(),
                    request.getImageNames());

            articleRepository.save(article);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create article: " + e.getMessage());
        }
    }

    @Transactional
    public void updateArticleById(UpdateArticleRequest request) {
        Article article = articleRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with id " + request.getId() + " not found."));

        article.update(request.getTitle(), request.getContent());
        articleRepository.save(article);
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    private List<TitleDTO> createTitleDTOList(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;

        List<Article> allArticles = articleRepository.findAll();
        List<TitleDTO> titleDTOList = new ArrayList<>();

        endIndex = Math.min(endIndex, allArticles.size()); // outOfIndex 방지

        for (int i = startIndex; i < endIndex; i++) {
            Article article = allArticles.get(i);
            TitleDTO titleDTO = new TitleDTO(
                    article.getId(),
                    article.getTitle(),
                    article.getAuthor(),
                    article.getCreateAt()
            );
            titleDTOList.add(titleDTO);
        }
        return titleDTOList;
    }

    private ArticleDTO convertToArticleDTO(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getContent(),
                article.getCreateAt(),
                article.getImageNames()
        );
    }
}
