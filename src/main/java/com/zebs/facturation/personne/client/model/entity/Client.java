package com.zebs.facturation.client.model.entity;

import com.zebs.facturation.factureclient.model.entity.FactureClient;
import com.zebs.facturation.model.entity.Personne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@Data
public class Client extends Personne {

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @PodamExclude
    private Set<FactureClient> factures;

}