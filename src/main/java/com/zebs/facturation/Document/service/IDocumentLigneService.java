package com.zebs.facturation.Document.service;

import com.zebs.facturation.model.entity.DocumentLigne;

import java.util.List;
import java.util.UUID;

public interface IDocumentLigneService<T extends DocumentLigne> {
    T save(T ligne);
    List<T> findAll();
    T findById(final UUID id);
    void delete(T ligne);
    void deleteById(UUID id);
    T update(T ligne, UUID id);
}
