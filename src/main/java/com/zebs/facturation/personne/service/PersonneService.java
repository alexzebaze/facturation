package com.zebs.facturation.personne.service;


import com.zebs.facturation.personne.model.entity.Personne;

import java.util.List;
import java.util.UUID;

public interface PersonneService<T extends Personne> {
    T save(T personne);
    List<T> findAll();
    T findById(final UUID id);
    void delete(T personne);
    void deleteById(UUID id);
    T update(T personne, UUID id);
}

