package com.zebs.facturation.tva.common.util;

public enum TvaConstant {
    TVA_NOT_FOUND("Tva introuvable");

    private String label;

    TvaConstant(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
