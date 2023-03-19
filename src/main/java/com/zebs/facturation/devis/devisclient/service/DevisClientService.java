package com.zebs.facturation.devis.devisclient.service;

import com.zebs.facturation.devis.devisclient.common.util.DevisClientConstant;
import com.zebs.facturation.devis.devisclient.dao.DevisClientDao;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import com.zebs.facturation.personne.client.common.exception.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DevisClientServiceImpl implements IDevisClientService {

    private final String devisClientNotFound = DevisClientConstant.DEVIS_CLIENT_NOT_FOUND.getLabel();

    @Autowired
    private DevisClientDao devisClientDao;

    @Override
    public DevisClient save(DevisClient devis) {
        try {
            return devisClientDao.save(devis);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<DevisClient> findAll() {
        return devisClientDao.findAll();
    }

    @Override
    public DevisClient findById(UUID id) {
        return devisClientDao.findById(id).orElseThrow(() -> new ClientException(devisClientNotFound, HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(DevisClient devis) {
        try {
            deleteById(devis.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<DevisClient> clientExist = devisClientDao.findById(id);
        if(clientExist.isPresent()){
            devisClientDao.deleteById(id);
            return;
        }
        throw new ClientException(devisClientNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public DevisClient update(DevisClient devis, UUID id) {
        if(!devis.getId().equals(id))
        throw new ClientException(devisClientNotFound, HttpStatus.NOT_FOUND);

        Optional<DevisClient> clientExist = devisClientDao.findById(id);
        if(clientExist.isPresent()){
            return devisClientDao.save(devis);
        }
        throw new ClientException(devisClientNotFound, HttpStatus.NOT_FOUND);
    }
}
