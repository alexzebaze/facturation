package com.zebs.facturation.projet.mapper;

import com.zebs.facturation.projet.model.entity.ProjetStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProjetStatusConverter implements AttributeConverter<ProjetStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProjetStatus status) {
        return status.getValue();
    }

    @Override
    public ProjetStatus convertToEntityAttribute(Integer value) {
        return Stream.of(ProjetStatus.values())
                .filter(s -> s.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
