package com.zebs.facturation.personne.client.common.util;

public enum  PersonneConstant {
    PERSONNE_NOT_FOUND("Client introuvable");

    private String label;

    PersonneConstant(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
