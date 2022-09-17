package com.zebs.facturation.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @ApiModelProperty(notes = "Date de création du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date dateCreated = new Date();

    @ApiModelProperty(notes = "Date de mise à jour du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date dateUpdated = new Date();

}
