package com.zebs.facturation.devis.devisclient.service.status;

import com.zebs.facturation.Document.service.DocumentStatusState;
import com.zebs.facturation.devis.devisclient.model.entity.DevisClient;
import com.zebs.facturation.devis.model.entity.DevisStatus;

public class DevisStatusAttente implements DocumentStatusState<DevisClient> {

    @Override
    public void statusAction(DevisClient devis) {
        devis.setStatus(DevisStatus.EN_ATTENTE);
        //action à effectuer quand devis passé en attente
    }
}
