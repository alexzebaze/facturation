package com.zebs.facturation.reglement.model.entity;

import com.zebs.facturation.Facture.facturefournisseur.model.entity.FactureFournisseur;
import com.zebs.facturation.model.entity.Base;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reglements")
public class Reglement extends Base {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FactureFournisseur facture;

}