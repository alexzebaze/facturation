package com.zebs.facturation.personne.client.common.util;

public enum ClientConstant {
    CLIENT_NOT_FOUND("Client introuvable");

    private String label;

    ClientConstant(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
