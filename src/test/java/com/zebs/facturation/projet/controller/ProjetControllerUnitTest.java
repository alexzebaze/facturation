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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProjetController.class)
public class ProjetControllerUnitTest {

    @MockBean
    private ProjetService projetService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<Projet> captorProjet;

    @Captor
    ArgumentCaptor<UUID> captorUuid;

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

        when(projetService.findAll()).thenReturn(projets);

        mockMvc.perform(get(PATH + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data.[0].id", is(String.valueOf(projets.get(0).getId()))))
                .andExpect(jsonPath("$.data.[0].titre", is(projets.get(0).getTitre())));

        verify(projetService).findAll();
    }

    @Test
    void getProjet() throws Exception {

        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetService.findById(isA(UUID.class))).thenReturn(projet);

        mockMvc.perform(get(PATH + "/"+projet.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));

        verify(projetService).findById(captorUuid.capture());

        assertEquals(projet.getId(), captorUuid.getValue());
    }

    @Test
    void itShouldNotGetProjetWhenHisIdNotSuch() throws Exception {
        UUID id = UUID.randomUUID();

        doThrow(ProjetException.class).when(projetService).findById(any(UUID.class));

        mockMvc.perform(get(PATH + "/"+id))
                .andExpect(status().isNotFound() );

        verify(projetService).findById(captorUuid.capture());
        assertEquals(id, captorUuid.getValue());
    }

    @Test
    void deleteProjetById() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        doNothing().when(projetService).deleteById(any(UUID.class));

        mockMvc.perform(delete(PATH + "/"+projet.getId()))
                .andExpect(status().isOk() );

        verify(projetService).deleteById(captorUuid.capture());

        assertEquals(projet.getId(), captorUuid.getValue());
    }

    @Test
    void itShouldNotDeleteProjetWhenHisIdNotSuch() throws Exception {
        UUID id = UUID.randomUUID();

        doThrow(ProjetException.class).when(projetService).deleteById(any(UUID.class));

        mockMvc.perform(delete(PATH + "/"+id))
                .andExpect(status().isNotFound() );

        verify(projetService).deleteById(captorUuid.capture());

        assertEquals(id, captorUuid.getValue());
    }

    @Test
    void deleteProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        doNothing().when(projetService).delete(projet);

        mockMvc.perform(delete(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk() );

        verify(projetService).delete(captorProjet.capture());

        assertThat(objectMapper.writeValueAsString(projet))
                .isEqualToIgnoringCase(objectMapper.writeValueAsString(captorProjet.getValue()));
    }

    @Test
    void itShouldNotDeleteProjetWhenThereNotSuch() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        doThrow(ProjetException.class).when(projetService).delete(isA(Projet.class));

        mockMvc.perform(delete(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isNotFound() );

        verify(projetService).delete(captorProjet.capture());

        assertThat(objectMapper.writeValueAsString(projet))
                .isEqualToIgnoringCase(objectMapper.writeValueAsString(captorProjet.getValue()));
    }

    @Test
    void saveProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        when(projetService.save(any(Projet.class))).thenReturn(projet);

        mockMvc.perform(post(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));

        verify(projetService).save(captorProjet.capture());

        assertThat(objectMapper.writeValueAsString(projet))
                .isEqualToIgnoringCase(objectMapper.writeValueAsString(captorProjet.getValue()));
    }

    @Test
    void itShouldNotSaveProjetWhenThereNotSuch() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        doThrow(ProjetException.class).when(projetService).save(isA(Projet.class));

        mockMvc.perform(post(PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isNotFound() );

        verify(projetService).save(captorProjet.capture());

        assertThat(objectMapper.writeValueAsString(projet))
                .isEqualToIgnoringCase(objectMapper.writeValueAsString(captorProjet.getValue()));
    }

    @Test
    void updateProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        projet.setTitre("Projet");
        when(projetService.updateProjet(any(Projet.class), any(UUID.class))).thenReturn(projet);

        mockMvc.perform(put(PATH + "/"+projet.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));

        verify(projetService).updateProjet(captorProjet.capture(), captorUuid.capture());

        assertThat(projet.getId()).isEqualTo(captorUuid.getValue());
        assertThat(objectMapper.writeValueAsString(projet))
                .isEqualToIgnoringCase(objectMapper.writeValueAsString(captorProjet.getValue()));
    }

    @Test
    void updatePartialProjet() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        projet.setTitre("Projet");
        when(projetService.updatePartialProjet(any(Projet.class), isA(UUID.class))).thenReturn(projet);

        mockMvc.perform(patch(PATH + "/"+projet.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectConverter.objectToJson(projet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(projet.getId()))))
                .andExpect(jsonPath("$.data.titre", is(projet.getTitre())));

        verify(projetService).updatePartialProjet(captorProjet.capture(), captorUuid.capture());

        assertThat(projet.getId()).isEqualTo(captorUuid.getValue());
        assertThat(objectMapper.writeValueAsString(projet))
                .isEqualToIgnoringCase(objectMapper.writeValueAsString(captorProjet.getValue()));
    }
}