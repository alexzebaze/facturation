package com.zebs.facturation.model.entity;

import com.zebs.facturation.article.model.entity.Article;
import com.zebs.facturation.tva.model.entity.Tva;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
@Data
public abstract class DocumentLigne extends Base {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    protected Tva tva;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
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
