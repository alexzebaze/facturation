package com.zebs.facturation.commun.File;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfo {
    private String name;
    private String url;
    private String type;
    private long size;
}
