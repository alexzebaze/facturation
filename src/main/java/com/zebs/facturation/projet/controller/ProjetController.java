package com.zebs.facturation.projet.controller;

import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import com.zebs.facturation.projet.model.entity.Projet;
import com.zebs.facturation.projet.service.ProjetService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/projets")
@Api(description="API pour les operations CRUD sur les projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<Projet>>> getProjets(){
        List<Projet> projets = projetService.findAll();

        return ResponseHandler.generateResponse(projets, HttpStatus.OK, "get all projects");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Projet>> getProjet(@PathVariable UUID id){
        Projet projet = projetService.findById(id);

        return ResponseHandler.generateResponse(projet, HttpStatus.OK, "get project id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Projet>> deleteProjetById(@PathVariable UUID id){
        projetService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete project id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<Projet>> deleteProjet(@RequestBody Projet projet){
        projetService.delete(projet);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete project id "+projet.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<Projet>> saveProjet(@RequestBody Projet projet){
        Projet projetSave = projetService.save(projet);

        return ResponseHandler.generateResponse(projetSave, HttpStatus.OK, "save project");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Projet>> updateProjet(@PathVariable UUID id, @Valid @RequestBody Projet projet){
        Projet projetUpdate = projetService.updateProjet(projet, id);

        return ResponseHandler.generateResponse(projetUpdate, HttpStatus.OK, "save update");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseModel<Projet>> updatePartialProjet(@PathVariable UUID id, @RequestBody Projet projet){
        System.out.println(projet);
        Projet projetUpdate = projetService.updatePartialProjet(projet, id);

        return ResponseHandler.generateResponse(projetUpdate, HttpStatus.OK, "save update partial");
    }

}