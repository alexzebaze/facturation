package com.zebs.facturation.projet.model.dto.response;

import com.zebs.facturation.projet.model.entity.ProjetStatus;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjetDto {
    private UUID id;
    private String titre;
    private String description;
    private Date dateCreated;
    private Date dateDebut;
    private Date dateFin;
    private ProjetStatus status;
}
