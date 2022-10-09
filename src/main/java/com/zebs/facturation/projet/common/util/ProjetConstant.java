package com.zebs.facturation.projet.common.util;

public enum ProjetConstant {
    PROJET_NOT_FOUND("Projet introuvable");

    private String label;

    ProjetConstant(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}