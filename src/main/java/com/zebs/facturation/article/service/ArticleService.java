package com.zebs.facturation.article.service;

import com.zebs.facturation.article.common.exception.ArticleException;
import com.zebs.facturation.article.common.util.ArticleConstant;
import com.zebs.facturation.article.dao.ArticleDao;
import com.zebs.facturation.article.model.entity.Article;
import com.zebs.facturation.personne.client.common.exception.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService implements IArticleService {

    private final String articleNotFound = ArticleConstant.ARTICLE_NOT_FOUND.getLabel();

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article save(Article article) {
        try {
            return articleDao.save(article);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article findById(UUID id) {
        return articleDao.findById(id).orElseThrow(() -> new ArticleException(articleNotFound, HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Article article) {
        try {
            deleteById(article.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<Article> articleExist = articleDao.findById(id);
        if(articleExist.isPresent()){
            articleDao.deleteById(id);
            return;
        }
        throw new ArticleException(articleNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public Article update(Article article, UUID id) {
        if(!article.getId().equals(id))
            throw new ClientException(articleNotFound, HttpStatus.NOT_FOUND);


        Optional<Article> articleExist = articleDao.findById(id);
        if(articleExist.isPresent()){
            article.setDateCreated(articleExist.get().getDateCreated());
            return articleDao.save(article);
        }
        throw new ClientException(articleNotFound, HttpStatus.NOT_FOUND);
    }
}

