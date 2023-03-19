package com.zebs.facturation.article.common.util;

public enum ArticleConstant {
    ARTICLE_NOT_FOUND("Article introuvable");

    private String label;

    ArticleConstant(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
