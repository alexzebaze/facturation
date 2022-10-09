package com.zebs.facturation.projet.service;

import com.zebs.facturation.projet.common.exception.ProjetException;
import com.zebs.facturation.projet.common.util.ProjetConstant;
import com.zebs.facturation.projet.dao.ProjetDao;
import com.zebs.facturation.projet.model.entity.Projet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetServiceImplTest {

    @InjectMocks
    private ProjetServiceImpl projetService;

    @Mock
    private ProjetDao projetDao;

    @Captor
    ArgumentCaptor<Projet> captor;

    @Captor
    ArgumentCaptor<UUID> captorUuid;

    private static PodamFactory podamFactory;

    @BeforeAll
    public static void setUp(){
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void save() {
        Projet projet = podamFactory.manufacturePojo(Projet.class);
        when(projetDao.save(any(Projet.class))).thenReturn(projet);

        projetService.save(projet);

        verify(projetDao).save(captor.capture());
        assertThat(projet).isEqualTo(captor.getValue());
    }

    @Test
    void findAll() {
        List<Projet> projets = new ArrayList<>();
        projets.add(podamFactory.manufacturePojo(Projet.class));
        projets.add(podamFactory.manufacturePojo(Projet.class));

        when(projetDao.findAll()).thenReturn(projets);
        List<Projet> projetsExpect = projetService.findAll();

        verify(projetDao).findAll();
        assertAll(()->{
            assertThat(projetsExpect).hasSize(2);
            assertEquals(projets, projetsExpect);
        });
    }

    @Test
    void itShouldNotGetProjetWhenHisIdNotSuch() {
        UUID id = UUID.randomUUID();
        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projetService.findById(id))
                .isInstanceOf(ProjetException.class)
                .hasMessage(ProjetConstant.PROJET_NOT_FOUND.getLabel());

        verify(projetDao).findById(captorUuid.capture());
        assertEquals(id, captorUuid.getValue());
    }

    @Test
    void findById() throws Exception {
        UUID id = UUID.randomUUID();
        Optional<Projet> projet = Optional.ofNullable(podamFactory.manufacturePojo(Projet.class));
        when(projetDao.findById(any(UUID.class))).thenReturn(projet);

        Projet projetExpect = projetService.findById(id);

        verify(projetDao).findById(captorUuid.capture());

        assertAll(()->{
            assertEquals(projet.get(), projetExpect);
            assertEquals(id, captorUuid.getValue());
        });
    }

    @Test
    void delete() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.ofNullable(projet));

        doNothing().when(projetDao).deleteById(any(UUID.class));

        projetService.delete(projet);

        verify(projetDao).deleteById(captorUuid.capture());

        assertEquals(projet.getId(), captorUuid.getValue());
    }

    @Test
    void itShouldNotDeleteProjetWhenHisIdNotSuch(){
        UUID id = UUID.randomUUID();

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(()->
                projetService.deleteById(id))
                .isInstanceOf(ProjetException.class)
                .hasMessage(ProjetConstant.PROJET_NOT_FOUND.getLabel());

        verify(projetDao).findById(captorUuid.capture());

        assertEquals(id, captorUuid.getValue());
    }

    @Test
    void deleteById() throws Exception {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.ofNullable(projet));

        doNothing().when(projetDao).deleteById(any(UUID.class));

        projetService.deleteById(projet.getId());

        verify(projetDao).deleteById(captorUuid.capture());

        assertEquals(projet.getId(), captorUuid.getValue());
    }

    @Test
    void itShouldNotUpdateProjetWhenHisIdNotSuch(){
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(()->
                projetService.updateProjet(projet, projet.getId()))
                .isInstanceOf(ProjetException.class)
                .hasMessage(ProjetConstant.PROJET_NOT_FOUND.getLabel());

        verify(projetDao).findById(captorUuid.capture());

        assertEquals(projet.getId(), captorUuid.getValue());
    }

    @Test
    void updateProjet() {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.ofNullable(projet));
        when(projetDao.save(any(Projet.class))).thenReturn(projet);

        Projet projetExpect = projetService.updateProjet(projet, projet.getId());

        verify(projetDao).findById(captorUuid.capture());
        verify(projetDao).save(captor.capture());

        assertAll(() -> {
            assertEquals(projet.getId(), captorUuid.getValue());
            assertEquals(projet, projetExpect);
        });
    }

    @Test
    void itShouldNotUpdatePartialProjetWhenHisIdNotSuch(){
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        UUID id = projet.getId();
        assertThatThrownBy(()->
                projetService.updatePartialProjet(projet, id))
                .isInstanceOf(ProjetException.class)
                .hasMessage(ProjetConstant.PROJET_NOT_FOUND.getLabel());

        verify(projetDao).findById(captorUuid.capture());

        assertEquals(id, captorUuid.getValue());
    }

    @Test
    void updatePartialProjet() {
        Projet projet = podamFactory.manufacturePojo(Projet.class);

        when(projetDao.findById(any(UUID.class))).thenReturn(Optional.ofNullable(projet));
        when(projetDao.save(any(Projet.class))).thenReturn(projet);

        Projet projetExpect = projetService.updatePartialProjet(projet, projet.getId());

        verify(projetDao).findById(captorUuid.capture());
        verify(projetDao).save(captor.capture());

        assertAll(() -> {
            assertEquals(projet.getId(), captorUuid.getValue());
            assertEquals(projet, projetExpect);
        });
    }
}