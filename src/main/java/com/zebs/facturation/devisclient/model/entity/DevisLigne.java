package com.zebs.facturation.devisclient.model.entity;

import com.zebs.facturation.model.entity.DocumentLigne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "devis_lignes")
@Data
public class DevisLigne extends DocumentLigne {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DevisClient devis;
}