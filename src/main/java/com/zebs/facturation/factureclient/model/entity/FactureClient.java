package com.zebs.facturation.factureclient.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zebs.facturation.client.model.entity.Client;
import com.zebs.facturation.devisclient.model.entity.DevisClient;
import com.zebs.facturation.facture.model.entity.FactureStatus;
import com.zebs.facturation.model.entity.Document;
import com.zebs.facturation.projet.model.entity.Projet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "factures_clients")
@Data
@EqualsAndHashCode(callSuper = true)
public class FactureClient extends Document {

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.DETACH)
    private DevisClient devis;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.DETACH)
    private Projet projet;

    @ApiModelProperty(notes = "Status de la facture")
    @NotNull(message = "Le status ne peut etre null")
    private FactureStatus status;

}