package com.zebs.facturation.projet.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zebs.facturation.factureclient.model.entity.FactureClient;
import com.zebs.facturation.facturefournisseur.model.entity.FactureFournisseur;
import com.zebs.facturation.model.entity.Base;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projets")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Projet extends Base {

    @ApiModelProperty(notes = "Nom du projet")
    @NotBlank(message = "Le Titre du projet ne peut etre vide")
    @Column(nullable = false)
    private String titre;

    @ApiModelProperty(notes = "Description du projet")
    @Lob
    private String description;

    @ApiModelProperty(notes = "Date de debut du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date dateDebut;

    @ApiModelProperty(notes = "Date de fin du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date dateFin;

    @ApiModelProperty(notes = "Status du projet")
    @NotNull(message = "Le status ne peut etre null")
    private ProjetStatus status;

    @OneToMany(mappedBy = "projet")
    @PodamExclude
    private Set<FactureFournisseur> facturesFournisseurs;

    @OneToMany(mappedBy = "projet")
    @PodamExclude
    private Set<FactureClient> facturesClients;

}
