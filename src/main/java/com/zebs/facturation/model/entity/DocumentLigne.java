package com.zebs.facturation.model.entity;

import com.zebs.facturation.article.model.entity.Article;
import com.zebs.facturation.tva.model.entity.Taxe;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class DocumentLigne extends Base {

    @ManyToOne(cascade = CascadeType.DETACH)
    protected Taxe taxe;

    @ManyToOne(cascade = CascadeType.DETACH)
    protected Article article;

    @Lob
    protected String designation;
    protected String unite;
    protected Double quantite;
    protected Double prixAchat;
    protected Double prixVenteHt;
    protected Double prixVenteTtc;
    protected Double remise;
}