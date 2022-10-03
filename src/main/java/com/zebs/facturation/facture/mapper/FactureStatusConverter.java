package com.zebs.facturation.facture.mapper;

import com.zebs.facturation.facture.model.entity.FactureStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class FactureStatusConverter implements AttributeConverter<FactureStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FactureStatus status) {
        return status.getValue();
    }

    @Override
    public FactureStatus convertToEntityAttribute(Integer value) {
        return Stream.of(FactureStatus.values())
                .filter(s -> s.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
