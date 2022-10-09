package com.zebs.facturation.projet.dao;

import com.zebs.facturation.projet.model.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjetDao extends JpaRepository<Projet, UUID> {
}