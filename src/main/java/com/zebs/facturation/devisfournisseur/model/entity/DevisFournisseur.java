package com.zebs.facturation.devisfournisseur.model.entity;

import com.zebs.facturation.fournisseur.model.entity.Fournisseur;
import com.zebs.facturation.devis.model.entity.DevisStatus;
import com.zebs.facturation.model.entity.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "devis_fournisseurs")
@EqualsAndHashCode(callSuper = true)
public class DevisFournisseur extends Document {

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fournisseur fournisseur;

    @ApiModelProperty(notes = "Status du devis")
    @NotNull(message = "Le status ne peut etre null")
    private DevisStatus status;
}