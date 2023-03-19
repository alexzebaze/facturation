package com.zebs.facturation.devis.devisclient.dao;

import com.zebs.facturation.devis.devisclient.model.entity.DevisClientLigne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevisClientLigneDao extends JpaRepository<DevisClientLigne, UUID> {
}
