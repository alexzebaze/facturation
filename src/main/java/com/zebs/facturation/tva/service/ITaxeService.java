package com.zebs.facturation.tva.service;

import com.zebs.facturation.tva.model.entity.Taxe;

import java.util.List;
import java.util.UUID;

public interface ITaxeService {
    Taxe save(Taxe personne);
    List<Taxe> findAll();
    Taxe findById(final UUID id);
    void delete(Taxe personne);
    void deleteById(UUID id);
    Taxe update(Taxe personne, UUID id);
}
