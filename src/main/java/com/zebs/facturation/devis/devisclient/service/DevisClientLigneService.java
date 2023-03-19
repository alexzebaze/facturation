package com.zebs.facturation.devis.devisclient.service;

import com.zebs.facturation.devis.devisclient.common.exception.DevisClientException;
import com.zebs.facturation.devis.devisclient.common.util.DevisClientConstant;
import com.zebs.facturation.devis.devisclient.dao.DevisClientLigneDao;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClientLigne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DevisClientLigneService implements IDevisClientLigne {

    private final String devisLigneNotFound = DevisClientConstant.DEVIS_LIGNE_NOT_FOUND.getLabel();

    @Autowired
    private DevisClientLigneDao devisLigneDao;

    @Override
    public DevisClientLigne save(DevisClientLigne ligne) {
        try {
            return devisLigneDao.save(ligne);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<DevisClientLigne> findAll() {
        return devisLigneDao.findAll();
    }

    @Override
    public DevisClientLigne findById(UUID id) {
        return devisLigneDao.findById(id).orElseThrow(() -> new DevisClientException(devisLigneNotFound, HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(DevisClientLigne ligne) {
        try {
            deleteById(ligne.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<DevisClientLigne> clientExist = devisLigneDao.findById(id);
        if(clientExist.isPresent()){
            devisLigneDao.deleteById(id);
            return;
        }
        throw new DevisClientException(devisLigneNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public DevisClientLigne update(DevisClientLigne ligne, UUID id) {
        if(!ligne.getId().equals(id))
            throw new DevisClientException(devisLigneNotFound, HttpStatus.NOT_FOUND);

        Optional<DevisClientLigne> clientExist = devisLigneDao.findById(id);
        if(clientExist.isPresent()){
            return devisLigneDao.save(ligne);
        }
        throw new DevisClientException(devisLigneNotFound, HttpStatus.NOT_FOUND);
    }
}

