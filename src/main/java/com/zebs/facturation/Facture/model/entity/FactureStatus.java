package com.zebs.facturation.facture.model.entity;

public enum FactureStatus {
    EN_ATTENTE(1, "EN_ATTENTE"),
    EN_COURS(2, "EN_COURS"),
    TERMINE(3, "TERMINE");

    private final int value;
    private final String label;

    FactureStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue(){
        return this.value;
    }

    public String getLabel(){
        return this.label;
    }
}