package com.zebs.facturation.projet.service;

import com.zebs.facturation.projet.model.entity.Projet;

import java.util.List;
import java.util.UUID;

public interface ProjetService {
    Projet save(Projet projet);
    List<Projet> findAll();
    Projet findById(final UUID id);
    void delete(Projet projet);
    void deleteById(UUID id);
    Projet updateProjet(Projet projet, UUID id);
    Projet updatePartialProjet(Projet projet, UUID id);
}