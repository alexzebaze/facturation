package com.zebs.facturation.fournisseur.dao;

import com.zebs.facturation.fournisseur.model.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FournisseurDao extends JpaRepository<Fournisseur, UUID> {
}
