package com.zebs.facturation.article.model.entity;

import com.zebs.facturation.bonlivraison.model.entity.BonLivraison;
import com.zebs.facturation.devisfournisseur.model.entity.DevisFournisseur;
import com.zebs.facturation.model.entity.Base;
import com.zebs.facturation.model.entity.Document;
import com.zebs.facturation.tva.model.entity.Tva;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "articles")
public class Article extends Base {
    private String libelle;
    private Double prixAchat;
    private Double prixVente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Tva tva;

}
