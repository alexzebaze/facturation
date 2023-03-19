package com.zebs.facturation.tva.service;

import com.zebs.facturation.tva.common.exception.TvaException;
import com.zebs.facturation.tva.common.util.TvaConstant;
import com.zebs.facturation.tva.dao.TvaDao;
import com.zebs.facturation.tva.model.entity.Taxe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaxeService implements ITaxeService {

    private final String tvaNotFound = TvaConstant.TVA_NOT_FOUND.getLabel();

    @Autowired
    private TvaDao tvaDao;

    @Transactional
    @Override
    public Taxe save(Taxe taxe) {
        try {
            return tvaDao.save(taxe);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<Taxe> findAll() {
        return tvaDao.findAll();
    }

    @Override
    public Taxe findById(UUID id) {
        return tvaDao.findById(id).orElseThrow(() -> new TvaException(tvaNotFound, HttpStatus.NOT_FOUND));

    }

    @Override
    public void delete(Taxe taxe) {
        try {
            deleteById(taxe.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<Taxe> clientExist = tvaDao.findById(id);
        if(clientExist.isPresent()){
            tvaDao.deleteById(id);
            return;
        }
        throw new TvaException(tvaNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public Taxe update(Taxe taxe, UUID id) {
        if(!taxe.getId().equals(id))
            throw new TvaException(tvaNotFound, HttpStatus.NOT_FOUND);

        Optional<Taxe> tvaExist = tvaDao.findById(id);
        if(tvaExist.isPresent()){
            taxe.setDateCreated(tvaExist.get().getDateCreated());
            return tvaDao.save(taxe);
        }
        throw new TvaException(tvaNotFound, HttpStatus.NOT_FOUND);

    }
}
