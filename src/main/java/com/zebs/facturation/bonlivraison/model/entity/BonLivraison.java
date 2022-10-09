package com.zebs.facturation.bonlivraison.model.entity;

import com.zebs.facturation.boncommande.model.entity.BonCommande;
import com.zebs.facturation.facturefournisseur.model.entity.FactureFournisseur;
import com.zebs.facturation.model.entity.Document;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bons_livraisons")
@Data
public class BonLivraison extends Document {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private FactureFournisseur facture;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private BonCommande commande;

}