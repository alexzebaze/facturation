package com.zebs.facturation.personne.model.entity;

import com.zebs.facturation.model.entity.Base;
import lombok.Data;

import javax.persistence.*;

/*
 ** Une personne peut etre à la fois client, fournisseur et utilisateur
 */

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Personne extends Base {

    protected String nom;
    protected String prenom;
    protected String email;
    protected String telephone;
    protected String mobile;
    protected String company;
    protected String siret;
    protected String numeroTva;
    protected String adresse;
    protected String adresse2;
    protected String codePostal;
    protected String ville;
    protected String pays;
    protected String siteWeb;


    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}