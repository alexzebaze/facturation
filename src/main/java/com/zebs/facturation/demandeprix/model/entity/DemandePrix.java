package com.zebs.facturation.demandeprix.model.entity;

import com.zebs.facturation.model.entity.Document;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "demandes_prix")
@Data
public class DemandePrix extends Document {
}
