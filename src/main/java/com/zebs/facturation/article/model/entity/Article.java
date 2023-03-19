package com.zebs.facturation.article.model.entity;

import com.zebs.facturation.devis.devisclient.model.entity.DevisClientLigne;
import com.zebs.facturation.model.entity.Base;
import com.zebs.facturation.model.entity.DocumentLigne;
import com.zebs.facturation.tva.model.entity.Taxe;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "articles")
public class Article extends Base implements Serializable {

    private String libelle;
    private Double prixAchat;
    private Double prixVente;
    @Lob
    private String image;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Taxe taxe;

    @OneToMany(mappedBy = "article")
    private Set<DevisClientLigne> devisClientLignes;

    @PreRemove
    public void removeFromAttachedEntity(){
        devisClientLignes.forEach(devisLigne ->devisLigne.setArticle(null));
    }

    @Override
    public String toString() {
        return "Article{" +
                ", id=" + id +
                "libelle='" + libelle + '\'' +
                ", prixAchat=" + prixAchat +
                ", prixVente=" + prixVente +
                ", image=" + image +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}