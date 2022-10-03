package com.zebs.facturation.fournisseur.dao;

import com.zebs.facturation.model.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonneDao extends JpaRepository<Personne, UUID> {
}
