package com.zebs.facturation.projet.mapper;

import com.zebs.facturation.projet.model.entity.ProjetStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjetStatusConverterTest {

    @InjectMocks
    private ProjetStatusConverter statusConverter;

    @Test
    void convertToDatabaseColumn(){
        Object statusValue = statusConverter.convertToDatabaseColumn(ProjetStatus.EN_COURS);
        assertAll(()->{
            assertThat(statusValue).isInstanceOf(Integer.class);
            assertEquals(statusValue, 2);
        });
    }

    @ParameterizedTest
    @CsvSource({"1, EN_ATTENTE", "2, EN_COURS", "3, TERMINE"})
    void convertToEntityAttribute(int value, String expected){
        Object status = statusConverter.convertToEntityAttribute(value);

        assertAll(()->{
            assertThat(status).isInstanceOf(ProjetStatus.class);
            assertEquals(expected, ((ProjetStatus)status).getLabel());
        });
    }

    @Test
    void itShouldNotGetProjetStatusWhenValueIsNotSuch(){
        assertThatThrownBy(()->statusConverter.convertToEntityAttribute(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}