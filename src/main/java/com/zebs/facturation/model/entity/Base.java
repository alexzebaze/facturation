package com.zebs.facturation.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class Base {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @NotNull(message = "L'id ne peut etre vide")
    @Column(nullable = false)
    protected UUID id;

    @ApiModelProperty(notes = "Date de création du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    @NotNull(message = "la date de creation ne peut etre vide")
    protected Date dateCreated;

    @ApiModelProperty(notes = "Date de mise à jour du projet")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    @NotNull(message = "la date de mise à jour ne peut etre vide")
    protected Date dateUpdated;

    @PreUpdate
    void preUpdate(){
        dateUpdated = new Date();
    }

    @PrePersist
    void prePersist(){
        dateCreated = new Date();
        dateUpdated = new Date();
    }
}