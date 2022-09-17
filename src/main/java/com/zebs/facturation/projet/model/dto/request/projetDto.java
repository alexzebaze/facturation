package com.zebs.facturation.projet.model.dto.request;

import com.zebs.facturation.projet.model.entity.ProjetStatus;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class projetDto {

    @Setter(AccessLevel.NONE)
    private UUID id;
    private String titre;
    private String description;
    private Date dateDebut;
    private ProjetStatus status;
}
