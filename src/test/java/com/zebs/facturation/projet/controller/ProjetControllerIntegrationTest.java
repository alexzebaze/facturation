package com.zebs.facturation.projet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebs.facturation.commun.util.ObjectConverter;
import com.zebs.facturation.projet.common.exception.ProjetException;
import com.zebs.facturation.projet.model.entity.Projet;
import com.zebs.facturation.projet.service.ProjetService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetControllerIntegrationTest {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static PodamFactory podamFactory;

    private String PATH = "/api/v1/projets";

    @BeforeAll
    public static void setUp(){
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void getProjets() throws Exception {

        List<Projet> projets = List.of(podamFactory.manufacturePojo(Projet.class),
                podamFactory.manufacturePojo(Projet.class));

        mockMvc.perform(get(PATH + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data.[0].id", is(String.valueOf(projets.get(0).getId()))))
                .andExpect(jsonPath("$.data.[0].titre", is(projets.get(0).getTitre())));
    }

    @Test
    void getProjet() throws Exception {

        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(get(PATH + "/"+projet.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));

    }

    @Test
    void itShouldNotGetProjetWhenHisIdNotSuch() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get(PATH + "/"+id))
                .andExpect(status().isNotFound() );

    }

    @Test
    void deleteProjetById() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(delete(PATH + "/"+projet.getId()))
                .andExpect(status().isOk() );
    }

    @Test
    void itShouldNotDeleteProjetWhenHisIdNotSuch() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete(PATH + "/"+id))
                .andExpect(status().isNotFound() );

    }

    @Test
    void deleteProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(delete(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk() );
    }

    @Test
    void itShouldNotDeleteProjetWhenThereNotSuch() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(delete(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isNotFound() );
    }

    @Test
    void saveProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(post(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));
    }

    @Test
    void itShouldNotSaveProjetWhenThereNotSuch() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(post(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isNotFound() );
    }

    @Test
    void updateProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        mockMvc.perform(put(PATH + "/"+projet.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));
    }

    @Test
    void updatePartialProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        mockMvc.perform(put(PATH + "/"+projet.getId()+"/partial")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));

    }
}
