package com.zebs.facturation.devisclient.model.entity;

import com.zebs.facturation.client.model.entity.Client;
import com.zebs.facturation.devis.model.entity.DevisStatus;
import com.zebs.facturation.factureclient.model.entity.FactureClient;
import com.zebs.facturation.model.entity.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "devis_clients")
@Data
public class DevisClient extends Document {

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @OneToMany(mappedBy = "devis", orphanRemoval = true)
    @PodamExclude
    private Set<DevisLigne> devisLignes;

    @ApiModelProperty(notes = "Status du devis")
    @NotNull(message = "Le status ne peut etre null")
    private DevisStatus status;

    @OneToMany(mappedBy = "devis")
    @PodamExclude
    private Set<FactureClient> factureClients;

}