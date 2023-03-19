package com.zebs.facturation.devis.devisclient.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.zebs.facturation.model.entity.DocumentLigne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "devis_lignes")
@Data
public class DevisClientLigne extends DocumentLigne {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference // Ceci omet devis dans devisClientLigne alerternative JsonIdentityInfo
    private DevisClient devis;

    @Override
    public String toString() {
        return "DevisClientLigne{" +
                ", taxe=" + taxe +
                ", article=" + article +
                ", designation='" + designation + '\'' +
                ", unite='" + unite + '\'' +
                ", quantite=" + quantite +
                ", prixAchat=" + prixAchat +
                ", prixVenteHt=" + prixVenteHt +
                ", prixVenteTtc=" + prixVenteTtc +
                ", remise=" + remise +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
