package com.zebs.facturation.facturefournisseur.model.entity;


import com.zebs.facturation.fournisseur.model.entity.Fournisseur;
import com.zebs.facturation.bonlivraison.model.entity.BonLivraison;
import com.zebs.facturation.devisclient.model.entity.DevisClient;
import com.zebs.facturation.devisfournisseur.model.entity.DevisFournisseur;
import com.zebs.facturation.facture.model.entity.FactureStatus;
import com.zebs.facturation.model.entity.Document;
import com.zebs.facturation.projet.model.entity.Projet;
import com.zebs.facturation.reglement.model.entity.Reglement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "factures_fournisseurs")
@Data
@EqualsAndHashCode(callSuper = true)
public class FactureFournisseur extends Document {

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fournisseur fournisseur;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private DevisFournisseur devisFournisseur;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private DevisClient devisClient;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.DETACH)
    private Projet projet;

    @OneToMany(mappedBy = "facture")
    @PodamExclude
    private Set<BonLivraison> bonLivraison;

    @OneToMany(mappedBy = "facture", orphanRemoval = true)
    @PodamExclude
    private Set<Reglement> reglements;

    @ApiModelProperty(notes = "Status de la facture")
    @NotNull(message = "Le status ne peut etre null")
    private FactureStatus status;

}