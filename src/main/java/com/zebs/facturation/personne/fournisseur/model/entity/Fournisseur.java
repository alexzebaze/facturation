package com.zebs.facturation.fournisseur.model.entity;

import com.zebs.facturation.facturefournisseur.model.entity.FactureFournisseur;
import com.zebs.facturation.model.entity.Personne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fournisseurs")
@Data
public class Fournisseur extends Personne {

    @OneToMany(mappedBy = "fournisseur", orphanRemoval = true)
    @PodamExclude
    private Set<FactureFournisseur> factures;
}