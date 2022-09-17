package com.zebs.facturation.projet.mapper;

import com.zebs.facturation.projet.model.dto.response.ProjetDto;
import com.zebs.facturation.projet.model.entity.Projet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjetMapperTest {

    @InjectMocks
    private ProjetMapper projetMapper;

    @Spy
    private ModelMapper modelMapper;

    private static PodamFactory podamFactory;

    @BeforeAll
    public static void setUp(){
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void convertToEntity(){
        ProjetDto projetDto = podamFactory.manufacturePojo(ProjetDto.class);
        Object projet = projetMapper.convertToEntity(projetDto);

        assertAll(()->{
            assertThat(projet).isInstanceOf(Projet.class);
            assertEquals(projetDto.getId(), ((Projet)projet).getId());
            assertEquals(projetDto.getTitre(), ((Projet)projet).getTitre());
            assertEquals(projetDto.getDateCreated(), ((Projet)projet).getDateCreated());
        });
    }

    @Test
    void convertToDto(){
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        Object projetDto = projetMapper.convertToDto(projet);

        assertAll(()->{
            assertThat(projetDto).isInstanceOf(ProjetDto.class);
            assertEquals(projet.getId(), ((ProjetDto)projetDto).getId());
            assertEquals(projet.getTitre(), ((ProjetDto)projetDto).getTitre());
            assertEquals(projet.getDateCreated(), ((ProjetDto)projetDto).getDateCreated());
        });
    }
}