package com.zebs.facturation.tva.model.entity;

import com.zebs.facturation.model.entity.Base;
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