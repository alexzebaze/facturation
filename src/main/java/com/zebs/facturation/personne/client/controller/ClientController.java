package com.zebs.facturation.personne.client.controller;

import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import com.zebs.facturation.personne.client.model.entity.Client;
import com.zebs.facturation.personne.service.PersonneService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/clients")
@Api(description="API pour les operations CRUD sur les clients")
public class ClientController {

    @Autowired
    private PersonneService<Client> clientService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<Client>>> getClients(){
        List<Client> clients = clientService.findAll();

        return ResponseHandler.generateResponse(clients, HttpStatus.OK, "get all clients");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Client>> getClient(@PathVariable UUID id){
        Client client = clientService.findById(id);

        return ResponseHandler.generateResponse(client, HttpStatus.OK, "get client id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Client>> deleteClientById(@PathVariable UUID id){
        clientService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete client id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<Client>> deleteClient(@RequestBody Client client){
        clientService.delete(client);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete client id "+client.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<Client>> saveClient(@RequestBody Client client){
        Client clientSave = clientService.save(client);

        return ResponseHandler.generateResponse(clientSave, HttpStatus.OK, "save client");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Client>> updateClient(@PathVariable UUID id, @Valid @RequestBody Client client){
        Client clientUpdate = clientService.update(client, id);
        return ResponseHandler.generateResponse(clientUpdate, HttpStatus.OK, "save update");
    }
}
