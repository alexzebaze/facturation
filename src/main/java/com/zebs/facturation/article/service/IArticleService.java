package com.zebs.facturation.article.service;

import com.zebs.facturation.article.model.entity.Article;

import java.util.List;
import java.util.UUID;

public interface IArticleService {
    Article save(Article article);
    List<Article> findAll();
    Article findById(final UUID id);
    void delete(Article article);
    void deleteById(UUID id);
    Article update(Article article, UUID id);
}
