package com.zebs.facturation.devis.devisclient.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.zebs.facturation.Document.service.DocumentStatusState;
import com.zebs.facturation.personne.client.model.entity.Client;
import com.zebs.facturation.devis.model.entity.DevisStatus;
import com.zebs.facturation.Facture.factureclient.model.entity.FactureClient;
import com.zebs.facturation.model.entity.Document;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "devis_clients")
@Data
public class DevisClient extends Document {

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @OneToMany(mappedBy = "devis", orphanRemoval = true)
    @PodamExclude
    @ApiModelProperty(hidden = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    //@JsonManagedReference // Pour gerer la recursivité infinie
    private List<DevisClientLigne> devisLignes;

    @ApiModelProperty(notes = "Status du devis")
    @NotNull(message = "Le status ne peut etre null")
    private DevisStatus status;

    @OneToMany(mappedBy = "devis")
    @PodamExclude
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY, hidden = true)
    private List<FactureClient> factureClients;

    @Transient
    @JsonIgnore
    private DocumentStatusState<DevisClient> statusState;

    public void setStatus(DevisStatus status){
        this.status = status;
    }

    public void statusAction(){
        this.statusState.statusAction(this);
    }

    @Override
    public String toString() {
        return "DevisClient{" +
                "client=" + client +
                ", status=" + status +
                ", statusState=" + statusState +
                ", dateInvoice=" + dateInvoice +
                ", dateEcheance=" + dateEcheance +
                ", reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                ", document='" + document + '\'' +
                ", remise=" + remise +
                ", totalHT=" + totalHT +
                ", totalTTC=" + totalTTC +
                ", totalNetHt=" + totalNetHt +
                ", totalTaxe=" + totalTaxe +
                ", projet=" + projet +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}