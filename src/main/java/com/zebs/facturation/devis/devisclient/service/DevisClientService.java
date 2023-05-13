package com.zebs.facturation.devis.devisclient.service;

import com.zebs.facturation.commun.util.export.IExport;
import com.zebs.facturation.devis.devisclient.common.exception.DevisClientException;
import com.zebs.facturation.devis.devisclient.common.util.DevisClientConstant;
import com.zebs.facturation.devis.devisclient.dao.DevisClientDao;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DevisClientService implements IDevisClientService {

    private final String devisClientNotFound = DevisClientConstant.DEVIS_NOT_FOUND.getLabel();

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
    public List<DevisClient> findAll()
    {
        return devisClientDao.findAll();
    }

    @Override
    public DevisClient findById(UUID id) {
        return devisClientDao.findById(id).orElseThrow(() -> new DevisClientException(devisClientNotFound, HttpStatus.NOT_FOUND));
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
        throw new DevisClientException(devisClientNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public DevisClient update(DevisClient devis, UUID id) {
        if(!devis.getId().equals(id))
        throw new DevisClientException(devisClientNotFound, HttpStatus.NOT_FOUND);

        Optional<DevisClient> clientExist = devisClientDao.findById(id);
        if(clientExist.isPresent()){
            return devisClientDao.save(devis);
        }
        throw new DevisClientException(devisClientNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public String export(IExport export, DevisClient devis) {
        Map<String, Object> datas = new HashMap<>();
        // TODO map devis entity objet to MAP with Stream
        String filename = export.export(datas, "destination_dir", "filename.pdf");

        return filename;
    }
}
