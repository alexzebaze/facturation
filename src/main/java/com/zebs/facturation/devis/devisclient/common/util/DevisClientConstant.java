package com.zebs.facturation.devis.devisclient.common.util;

public enum  DevisClientConstant {
    DEVIS_NOT_FOUND("Devis introuvable"),
    DEVIS_LIGNE_NOT_FOUND("Devis article introuvable");

    private String label;

    DevisClientConstant(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
