package com.zebs.facturation.article.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.Nullable;
import com.zebs.facturation.article.model.entity.Article;
import com.zebs.facturation.article.service.ArticleService;
import com.zebs.facturation.article.service.IArticleService;
import com.zebs.facturation.commun.File.FileInfo;
import com.zebs.facturation.commun.File.IFile;
import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/articles")
@Api(description="API pour les operations CRUD sur les articles")
public class ArticleController {



    @Autowired
    private IArticleService articleService;

    @Autowired
    IFile fileService;

    @GetMapping("/index")
    public Authentication index(){

        Map<String, String> map = new HashMap<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth;
    }

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

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestBody Article article){


        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Article>> updateArticle(@PathVariable UUID id, @RequestParam("article") String articleparam, @RequestParam(value = "image", required=false) MultipartFile imagefile) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Article article = mapper.readValue(articleparam, Article.class);

        if(imagefile != null){
            try {
                FileInfo fileInfo = fileService.save(imagefile);
                article.setImage(fileInfo.getName());
            } catch (Exception e) {
                throw new RuntimeException("Could not upload the file: " + imagefile.getOriginalFilename() + ". Error: " + e.getMessage());
            }
        }

        Article articleUpdate = articleService.update(article, id);

        return ResponseHandler.generateResponse(articleUpdate, HttpStatus.OK, "save update");
    }
}
