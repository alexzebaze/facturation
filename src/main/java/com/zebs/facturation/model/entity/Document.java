package com.zebs.facturation.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zebs.facturation.projet.model.entity.Projet;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * la classe Document represente tout ce qui est
 * facture, bon de livraison, bon de commande, devis
 */

@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Document extends Base {

    @ApiModelProperty(notes = "Date de facturation")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date dateInvoice;

    @ApiModelProperty(notes = "Date d'echeance de la facture")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date dateEcheance;

    protected String reference;

    @Lob
    protected String description;
    protected String document;
    protected Double remise;

    @Column(name = "total_ht")
    protected BigDecimal totalHT;

    @Column(name = "total_ttc")
    protected BigDecimal totalTTC;

    @Column(name = "total_net")
    protected BigDecimal totalNet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Projet projet;



}