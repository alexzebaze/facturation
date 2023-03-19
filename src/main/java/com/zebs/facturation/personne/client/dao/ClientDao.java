package com.zebs.facturation.personne.client.dao;

import com.zebs.facturation.personne.client.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientDao extends JpaRepository<Client, UUID> {
}
