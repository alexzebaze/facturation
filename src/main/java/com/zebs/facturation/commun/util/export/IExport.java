package com.zebs.facturation.commun.util;

import com.sun.istack.Nullable;

import java.util.Map;

public interface IExport{
    public String export(Map data, String destination, @Nullable String filename);
}
