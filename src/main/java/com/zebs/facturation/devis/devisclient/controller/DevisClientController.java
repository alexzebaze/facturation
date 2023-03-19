package com.zebs.facturation.devis.devisclient.controller;

import com.zebs.facturation.commun.util.export.ExportExcel;
import com.zebs.facturation.commun.util.export.ExportPdf;
import com.zebs.facturation.commun.response.ResponseHandler;
import com.zebs.facturation.commun.response.ResponseModel;
import com.zebs.facturation.devis.devisclient.dao.DevisClientDao;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import com.zebs.facturation.devis.devisclient.service.IDevisClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/devisvente")
@Api(description="API pour les operations CRUD sur les devis clients")
public class DevisClientController {

    @Autowired
    private IDevisClientService devisClientService;

    @Autowired
    private DevisClientDao devisClientDao;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<List<DevisClient>>> getDevisClients(){
        List<DevisClient> devis = devisClientService.findAll();

        return ResponseHandler.generateResponse(devis, HttpStatus.OK, "get all devis");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<DevisClient>> getDevisClient(@PathVariable UUID id){
        DevisClient devis = devisClientService.findById(id);

        return ResponseHandler.generateResponse(devis, HttpStatus.OK, "get devis id "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<DevisClient>> deleteDevisClientById(@PathVariable UUID id){
        devisClientService.deleteById(id);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete devis id "+id);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseModel<DevisClient>> deleteDevisClient(@RequestBody DevisClient devis){
        devisClientService.delete(devis);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, "delete devis id "+devis.getId());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseModel<DevisClient>> saveDevisClient(@RequestBody DevisClient devis){
        DevisClient devisSave = devisClientService.save(devis);

        return ResponseHandler.generateResponse(devisSave, HttpStatus.OK, "save devis");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<DevisClient>> updateDevisClient(@PathVariable UUID id, @Valid @RequestBody DevisClient devis){
        DevisClient devisUpdate = devisClientService.update(devis, id);
        return ResponseHandler.generateResponse(devisUpdate, HttpStatus.OK, "save update");
    }

    @GetMapping("/{id}/export/{type}")
    public void export(@PathVariable UUID id, @PathVariable String type){
        DevisClient devis = devisClientService.findById(id);
        switch (type.toLowerCase()){
            case "pdf":
                devisClientService.export(new ExportPdf(), devis);
                break;
            case "excel":
                devisClientService.export(new ExportExcel(), devis);
                break;
            default:

        }
    }
}
