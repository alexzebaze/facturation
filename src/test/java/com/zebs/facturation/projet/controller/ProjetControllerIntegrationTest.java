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

import java.rmi.server.UID;
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

    private final String PATH = "/api/v1/projets";

    private  final UUID id = UUID.fromString("84dcd1cd-0034-46ad-a0f3-486a96e6502c");

    @BeforeAll
    public static void setUp(){
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void getProjets() throws Exception {

        mockMvc.perform(get(PATH + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.[0].id", is(String.valueOf(id))));
    }

    @Test
    void getProjet() throws Exception {

        mockMvc.perform(get(PATH + "/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(id))));

    }

    @Test
    void itShouldNotGetProjetWhenHisIdNotSuch() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get(PATH + "/"+id))
                .andExpect(status().isNotFound() );

    }

    @Test
    void deleteProjetById() throws Exception {

        mockMvc.perform(delete(PATH + "/84215921-abd4-4ad7-85ff-c0a7b21d6070"))
                .andExpect(status().isOk() );
    }

    @Test
    void itShouldNotDeleteProjetIfIdNotSuch() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete(PATH + "/"+id))
                .andExpect(status().isNotFound() );

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
        projet.setTitre("Projet");

        mockMvc.perform(post(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void updateProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        projet.setId(id);
        projet.setTitre("Projet");

        mockMvc.perform(put(PATH + "/"+projet.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))));
    }

    @Test
    void updatePartialProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        projet.setId(id);
        projet.setTitre("Projet");

        mockMvc.perform(patch(PATH + "/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))));

    }
}
