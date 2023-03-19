package com.zebs.facturation.tva.service;

import com.zebs.facturation.tva.model.entity.Tva;

import java.util.List;
import java.util.UUID;

public interface ITvaService {
    Tva save(Tva personne);
    List<Tva> findAll();
    Tva findById(final UUID id);
    void delete(Tva personne);
    void deleteById(UUID id);
    Tva update(Tva personne, UUID id);
}
