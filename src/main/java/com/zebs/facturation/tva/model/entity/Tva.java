package com.zebs.facturation.tva.model.entity;

import com.zebs.facturation.bonlivraison.model.entity.BonLivraison;
import com.zebs.facturation.devisfournisseur.model.entity.DevisFournisseur;
import com.zebs.facturation.model.entity.Base;
import com.zebs.facturation.model.entity.Document;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tvas")
public class Tva extends Base {

    private String valeur;

    @Lob
    private String description;
}
