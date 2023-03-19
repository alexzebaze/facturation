package com.zebs.facturation.Document.service;

import com.zebs.facturation.commun.util.export.IExport;
import com.zebs.facturation.model.entity.Document;

import java.util.List;
import java.util.UUID;

public interface IDocumentService<T extends Document> {
    T save(T document);
    List<T> findAll();
    T findById(final UUID id);
    void delete(T document);
    void deleteById(UUID id);
    T update(T document, UUID id);
    String export(IExport export, T document);
}


