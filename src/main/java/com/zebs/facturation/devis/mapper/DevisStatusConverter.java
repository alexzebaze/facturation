package com.zebs.facturation.devis.mapper;

import com.zebs.facturation.devis.model.entity.DevisStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DevisStatusConverter implements AttributeConverter<DevisStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DevisStatus status) {
        return status.getValue();
    }

    @Override
    public DevisStatus convertToEntityAttribute(Integer value) {
        return Stream.of(DevisStatus.values())
                .filter(s -> s.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}


