package com.zebs.facturation.tva.controller;

import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import com.zebs.facturation.tva.model.entity.Taxe;
import com.zebs.facturation.tva.service.ITaxeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/taxes")
@Api(description="API pour les operations CRUD sur les taxes")
public class TaxeController {
    @Autowired
    private ITaxeService taxeService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<Taxe>>> getTaxes(){
        List<Taxe> taxes = taxeService.findAll();

        return ResponseHandler.generateResponse(taxes, HttpStatus.OK, "get all taxes");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Taxe>> getTaxe(@PathVariable UUID id){
        Taxe taxe = taxeService.findById(id);

        return ResponseHandler.generateResponse(taxe, HttpStatus.OK, "get taxe id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Taxe>> deleteTaxeById(@PathVariable UUID id){
        taxeService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete taxe id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<Taxe>> deleteTaxe(@RequestBody Taxe taxe){
        taxeService.delete(taxe);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete taxe id "+taxe.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<Taxe>> saveTaxe(@RequestBody Taxe taxe){
        Taxe taxeSave = taxeService.save(taxe);

        return ResponseHandler.generateResponse(taxeSave, HttpStatus.OK, "save taxe");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Taxe>> updateTaxe(@PathVariable UUID id, @RequestBody Taxe taxe){
        Taxe taxeUpdate = taxeService.update(taxe, id);

        return ResponseHandler.generateResponse(taxeUpdate, HttpStatus.OK, "save update");
    }
}

