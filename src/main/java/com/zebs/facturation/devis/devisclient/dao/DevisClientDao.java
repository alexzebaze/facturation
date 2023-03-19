package com.zebs.facturation.devis.devisclient.dao;

import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DevisClientDao extends JpaRepository<DevisClient, UUID> {
}
