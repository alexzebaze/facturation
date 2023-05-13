package com.zebs.facturation.devis.devisclient.dao;

import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.UUID;

public interface DevisClientDao extends JpaRepository<DevisClient, UUID> {

    //@Query("SELECT d FROM DevisClient d")
    @Query( value = "SELECT * FROM devis_clients WHERE id = 'sds'", nativeQuery =true)
    public ArrayList<DevisClient> customFindAll();

}
