package com.zebs.facturation.reglement.model.entity;

import com.zebs.facturation.bonlivraison.model.entity.BonLivraison;
import com.zebs.facturation.devisfournisseur.model.entity.DevisFournisseur;
import com.zebs.facturation.facturefournisseur.model.entity.FactureFournisseur;
import com.zebs.facturation.model.entity.Base;
import com.zebs.facturation.model.entity.Document;
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