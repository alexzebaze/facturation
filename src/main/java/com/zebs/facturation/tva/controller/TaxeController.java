package com.zebs.facturation.tva.controller;

import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import com.zebs.facturation.taxe.model.entity.Tva;
import com.zebs.facturation.taxe.service.TvaService;
import com.zebs.facturation.tva.model.entity.Tva;
import com.zebs.facturation.tva.service.ITvaService;
import com.zebs.facturation.tva.service.TvaService;
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
public class TvaController {
    @Autowired
    private ITvaService taxeService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<Tva>>> getTaxes(){
        List<Tva> taxes = taxeService.findAll();

        return ResponseHandler.generateResponse(taxes, HttpStatus.OK, "get all taxes");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Tva>> getTva(@PathVariable UUID id){
        Tva taxe = taxeService.findById(id);

        return ResponseHandler.generateResponse(taxe, HttpStatus.OK, "get taxe id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Tva>> deleteTvaById(@PathVariable UUID id){
        taxeService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete taxe id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<Tva>> deleteTva(@RequestBody Tva taxe){
        taxeService.delete(taxe);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete taxe id "+taxe.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<Tva>> saveTva(@RequestBody Tva taxe){
        Tva taxeSave = taxeService.save(taxe);

        return ResponseHandler.generateResponse(taxeSave, HttpStatus.OK, "save taxe");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Tva>> updateTva(@PathVariable UUID id, @Valid @RequestBody Tva taxe){
        Tva taxeUpdate = taxeService.updateTva(taxe, id);

        return ResponseHandler.generateResponse(taxeUpdate, HttpStatus.OK, "save update");
    }
}
