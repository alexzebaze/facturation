package com.zebs.facturation.personne.client.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zebs.facturation.Facture.factureclient.model.entity.FactureClient;
import com.zebs.facturation.personne.model.entity.Personne;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@Data
public class Client extends Personne {

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @PodamExclude
    @ApiModelProperty(hidden = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Set<FactureClient> factures;

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", company='" + company + '\'' +
                ", siret='" + siret + '\'' +
                ", numeroTva='" + numeroTva + '\'' +
                ", adresse='" + adresse + '\'' +
                ", adresse2='" + adresse2 + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", siteWeb='" + siteWeb + '\'' +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}