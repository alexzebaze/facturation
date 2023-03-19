package com.zebs.facturation.boncommande.model.entity;

import com.zebs.facturation.bonlivraison.model.entity.BonLivraison;
import com.zebs.facturation.devis.devisfournisseur.model.entity.DevisFournisseur;
import com.zebs.facturation.model.entity.Document;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bons_commandes")
public class BonCommande extends Document {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private DevisFournisseur devis;

    @OneToOne(mappedBy = "commande")
    private BonLivraison bonLivraison;

}