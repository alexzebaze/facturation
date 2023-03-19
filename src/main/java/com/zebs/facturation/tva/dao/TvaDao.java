package com.zebs.facturation.tva.dao;

import com.zebs.facturation.tva.model.entity.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TvaDao extends JpaRepository<Taxe, UUID> {
}
