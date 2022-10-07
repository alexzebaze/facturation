package com.zebs.facturation.demandeprix.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Address {

    private String country;
    private String city;
    private String postCode;
}

