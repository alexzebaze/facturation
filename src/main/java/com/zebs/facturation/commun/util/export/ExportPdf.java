package com.zebs.facturation.commun.util;

import com.sun.istack.Nullable;

import java.util.Map;

public class ExportPdf implements IExport {
    @Override
    public String export(Map data, String destination, @Nullable String filename) {
        // Traitement de la donnant à ajouter dans le PDF et retourn du lien ou nom du pdf
        return "filename.pdf";
    }
}
