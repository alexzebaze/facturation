package com.zebs.facturation.commun.File;

import com.zebs.facturation.commun.exception.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    IFile fileService;

    @PostMapping("/api/v1/uploadFile")
    public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            FileInfo fileInfo = fileService.save(file);

            return new ResponseEntity(fileInfo, HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException("Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }
    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<FileInfo[]> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

        try {
            List<FileInfo> filesSaved = Arrays.asList(files).stream()
                    .map(file->fileService.save(file))
                    .collect(Collectors.toList());

            return new ResponseEntity(filesSaved, HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException("Could not upload the files. Error: " + e.getMessage());
        }
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {

        Resource file = fileService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
