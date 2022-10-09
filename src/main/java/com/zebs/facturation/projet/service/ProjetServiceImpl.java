package com.zebs.facturation.projet.service;


import com.zebs.facturation.projet.common.exception.ProjetException;
import com.zebs.facturation.projet.common.util.ProjetConstant;
import com.zebs.facturation.projet.dao.ProjetDao;
import com.zebs.facturation.projet.model.entity.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjetServiceImpl implements ProjetService {

    private final String projetNotFound = ProjetConstant.PROJET_NOT_FOUND.getLabel();

    @Autowired
    ProjetDao projetDao;

    @Override
    public Projet save(Projet projet) {
        try {
            return projetDao.save(projet);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<Projet> findAll() {
        return projetDao.findAll();
    }

    @Override
    public Projet findById(UUID id) {
        return projetDao.findById(id).orElseThrow(() -> new ProjetException(projetNotFound, HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Projet projet) {
        try {
            deleteById(projet.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<Projet> projetExist = projetDao.findById(id);
        if(projetExist.isPresent()){
            projetDao.deleteById(id);
            return;
        }
        throw new ProjetException(projetNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public Projet updateProjet(Projet projet, UUID id) {
        if(!projet.getId().equals(id))
            throw new ProjetException(projetNotFound, HttpStatus.NOT_FOUND);

        Optional<Projet> projetExist = projetDao.findById(id);
        if(projetExist.isPresent()){
            return projetDao.save(projet);
        }
        throw new ProjetException(projetNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public Projet updatePartialProjet(Projet projetUpdate, UUID id) {
        if(!projetUpdate.getId().equals(id))
            throw new ProjetException(projetNotFound, HttpStatus.NOT_FOUND);

        try {
            Projet currentProjet = findById(id);

            if(projetUpdate.getTitre() != null)
                currentProjet.setTitre(projetUpdate.getTitre());
            if(projetUpdate.getDescription() != null)
                currentProjet.setDescription(projetUpdate.getDescription());
            if(projetUpdate.getDateDebut() != null)
                currentProjet.setDateDebut(projetUpdate.getDateDebut());
            if(projetUpdate.getDateFin() != null)
                currentProjet.setDateFin(projetUpdate.getDateFin());
            if(projetUpdate.getStatus() != null)
                currentProjet.setStatus(projetUpdate.getStatus());

            return projetDao.save(currentProjet);
        }
        catch (Exception e){
            throw (e);
        }
    }
}