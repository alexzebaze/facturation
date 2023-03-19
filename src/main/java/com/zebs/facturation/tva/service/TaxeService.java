package com.zebs.facturation.tva.service;

import com.zebs.facturation.tva.common.exception.TvaException;
import com.zebs.facturation.tva.common.util.TvaConstant;
import com.zebs.facturation.tva.dao.TvaDao;
import com.zebs.facturation.tva.model.entity.Tva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TvaService implements ITvaService {

    private final String tvaNotFound = TvaConstant.TVA_NOT_FOUND.getLabel();

    @Autowired
    private TvaDao tvaDao;

    @Override
    public Tva save(Tva tva) {
        try {
            return tvaDao.save(tva);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<Tva> findAll() {
        return tvaDao.findAll();
    }

    @Override
    public Tva findById(UUID id) {
        return tvaDao.findById(id).orElseThrow(() -> new TvaException(tvaNotFound, HttpStatus.NOT_FOUND));

    }

    @Override
    public void delete(Tva tva) {
        try {
            deleteById(tva.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<Tva> clientExist = tvaDao.findById(id);
        if(clientExist.isPresent()){
            tvaDao.deleteById(id);
            return;
        }
        throw new TvaException(tvaNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public Tva update(Tva tva, UUID id) {
        if(!tva.getId().equals(id))
            throw new TvaException(tvaNotFound, HttpStatus.NOT_FOUND);

        Optional<Tva> clientExist = tvaDao.findById(id);
        if(clientExist.isPresent()){
            return tvaDao.save(tva);
        }
        throw new TvaException(tvaNotFound, HttpStatus.NOT_FOUND);

    }
}
