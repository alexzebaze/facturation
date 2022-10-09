package com.zebs.facturation.projet.dao;

import com.zebs.facturation.projet.model.entity.Projet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjetDaoTest {

    @Autowired
    private ProjetDao projetDao;

    @ParameterizedTest
    @CsvSource({"84dcd1cd-0034-46ad-a0f3-486a96e6502c, projet10", "84215921-abd4-4ad7-85ff-c0a7b21d6070, projet11"})
    void  getProjet(UUID id, String excepted){
        Optional<Projet> projet =  projetDao.findById(id);
        assertAll(()->{
            assertTrue(projet.isPresent());
            assertEquals(projet.get().getTitre(), excepted);
        });
    }

    @Test
    void findAll(){
        List<Projet> projets = projetDao.findAll();
        assertThat(projets).hasSize(2);
    }
}