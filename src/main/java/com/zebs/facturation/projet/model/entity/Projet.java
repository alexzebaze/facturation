package com.zebs.facturation.projet.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zebs.facturation.model.entity.Base;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "projets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Projet extends Base {

    @ApiModelProperty(notes = "Nom du projet")
    @NotBlank(message = "Titre du projet ne peut etre vide")
    @NotNull
    private String titre;

    @ApiModelProperty(notes = "Description du projet")
    @Lob
    private String description;

    @ApiModelProperty(notes = "Date de debut du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateDebut;

    @ApiModelProperty(notes = "Date de fin du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateFin;

    @ApiModelProperty(notes = "Status du projet")
    private ProjetStatus status;
}
