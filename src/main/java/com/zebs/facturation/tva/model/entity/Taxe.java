package com.zebs.facturation.tva.model.entity;

import com.zebs.facturation.article.model.entity.Article;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClientLigne;
import com.zebs.facturation.model.entity.Base;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "taxes")
public class Taxe extends Base {

    private String valeur;
    private String nom;

    @OneToMany(mappedBy = "taxe")
    private Set<DevisClientLigne> devisClientLignes;

    @OneToMany(mappedBy = "taxe")
    private Set<Article> articles;

    @PreRemove
    public void removeFromAttachedEntity(){
        devisClientLignes.forEach(devisLigne ->devisLigne.setTaxe(null));
        articles.forEach(article ->article.setTaxe(null));
    }

    @Lob
    private String description;

    @Override
    public String toString() {
        return "Taxe{" +
                "valeur='" + valeur + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}