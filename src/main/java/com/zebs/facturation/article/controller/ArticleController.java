package com.zebs.facturation.article;

import com.zebs.facturation.article.model.entity.Article;
import com.zebs.facturation.article.service.ArticleService;
import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<Article>>> getArticles(){
        List<Article> articles = articleService.findAll();

        return ResponseHandler.generateResponse(articles, HttpStatus.OK, "get all articles");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Article>> getArticle(@PathVariable UUID id){
        Article article = articleService.findById(id);

        return ResponseHandler.generateResponse(article, HttpStatus.OK, "get article id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Article>> deleteArticleById(@PathVariable UUID id){
        articleService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete article id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<Article>> deleteArticle(@RequestBody Article article){
        articleService.delete(article);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete article id "+article.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<Article>> saveArticle(@RequestBody Article article){
        Article articleSave = articleService.save(article);

        return ResponseHandler.generateResponse(articleSave, HttpStatus.OK, "save article");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Article>> updateArticle(@PathVariable UUID id, @Valid @RequestBody Article article){
        Article articleUpdate = articleService.update(article, id);
        return ResponseHandler.generateResponse(articleUpdate, HttpStatus.OK, "save update");
    }
}
