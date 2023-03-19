package com.zebs.facturation.article.dao;

import com.zebs.facturation.article.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleDao extends JpaRepository<Article, UUID> {

}
