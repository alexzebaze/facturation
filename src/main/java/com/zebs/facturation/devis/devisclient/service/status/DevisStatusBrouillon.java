package com.zebs.facturation.devis.service;

import com.zebs.facturation.Document.service.DocumentStatusState;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;

public class DevisStatusBrouillon implements DocumentStatusState<DevisClient> {

    @Override
    public void statusAction(DevisClient document) {
        
    }
}
