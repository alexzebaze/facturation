package com.zebs.facturation.personne.client.service;

import com.zebs.facturation.personne.client.common.exception.ClientException;
import com.zebs.facturation.personne.client.common.util.ClientConstant;
import com.zebs.facturation.personne.client.dao.ClientDao;
import com.zebs.facturation.personne.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.zebs.facturation.personne.client.model.entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements PersonneService<Client> {

    private final String clientNotFound = ClientConstant.CLIENT_NOT_FOUND.getLabel();

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client save(Client client) {
        try {
            return clientDao.save(client);
        }
        catch (IllegalArgumentException e) {
            throw (e);
        }
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findById(UUID id) {
        return clientDao.findById(id).orElseThrow(() -> new ClientException(clientNotFound, HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Client client) {
        try {
            deleteById(client.getId());
        }
        catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        Optional<Client> clientExist = clientDao.findById(id);
        if(clientExist.isPresent()){
            clientDao.deleteById(id);
            return;
        }
        throw new ClientException(clientNotFound, HttpStatus.NOT_FOUND);
    }

    @Override
    public Client update(Client client, UUID id) {
        if(!client.getId().equals(id))
            throw new ClientException(clientNotFound, HttpStatus.NOT_FOUND);

        Optional<Client> clientExist = clientDao.findById(id);
        if(clientExist.isPresent()){
            return clientDao.save(client);
        }
        throw new ClientException(clientNotFound, HttpStatus.NOT_FOUND);
    }

}
