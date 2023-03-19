package com.zebs.facturation.devis.devisclient.controller;

import com.zebs.facturation.devis.devisclient.model.entity.DevisClientLigne;
import com.zebs.facturation.devis.devisclient.service.IDevisClientLigne;
import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/devisventeligne")
@Api(description="API pour les operations sur les ligne devis ventes")
public class DevisClientLigneController {

    @Autowired
    private IDevisClientLigne devisLigneService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<DevisClientLigne>>> getDevisClientLignes(){
        List<DevisClientLigne> devisLignes = devisLigneService.findAll();

        return ResponseHandler.generateResponse(devisLignes, HttpStatus.OK, "get all devisLignes");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<DevisClientLigne>> getDevisClientLigne(@PathVariable UUID id){
        DevisClientLigne devisLigne = devisLigneService.findById(id);

        return ResponseHandler.generateResponse(devisLigne, HttpStatus.OK, "get devisLigne id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<DevisClientLigne>> deleteDevisClientLigneById(@PathVariable UUID id){
        devisLigneService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete devisLigne id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<DevisClientLigne>> deleteDevisClientLigne(@RequestBody DevisClientLigne devisLigne){
        devisLigneService.delete(devisLigne);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete devisLigne id "+devisLigne.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<DevisClientLigne>> saveDevisClientLigne(@RequestBody DevisClientLigne devisLigne){
        DevisClientLigne devisLigneSave = devisLigneService.save(devisLigne);

        return ResponseHandler.generateResponse(devisLigneSave, HttpStatus.OK, "save devisLigne");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<DevisClientLigne>> updateDevisClientLigne(@PathVariable UUID id, @Valid @RequestBody DevisClientLigne devisLigne){
        DevisClientLigne devisLigneUpdate = devisLigneService.update(devisLigne, id);
        return ResponseHandler.generateResponse(devisLigneUpdate, HttpStatus.OK, "save update");
    }
}
