package com.zebs.facturation.fournisseur.dao;

import com.zebs.facturation.client.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {
}
