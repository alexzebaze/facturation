package com.zebs.facturation.Document.service;

import com.zebs.facturation.model.entity.Document;

public interface DocumentStatus<T extends Document> {
    public void statusAction(T document);
}

