package com.zebs.facturation.commun.File;

import com.zebs.facturation.commun.config.FileUploadProperties;
import com.zebs.facturation.commun.exception.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class FileService implements IFile {

    private final Path baseStorageLocation;

    @Autowired
    public FileService(FileUploadProperties fileUploadProperties) {
        this.baseStorageLocation = Paths.get(fileUploadProperties.getLocation());
    }

    @PostConstruct
    @Override
    public void init() {
        try {
            Files.createDirectories(this.baseStorageLocation.toAbsolutePath().normalize());
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not create upload dir!");
        }
    }

    @Override
    public FileInfo save(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();

            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.baseStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(this.baseStorageLocation.toString())
                    .path(fileName)
                    .toUriString();

            return new FileInfo(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());


        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException("Could not upload file");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = this.baseStorageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileException("Could not read the file!", HttpStatus.NOT_FOUND);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.baseStorageLocation.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.baseStorageLocation, 1)
                    .filter(path -> !path.equals(this.baseStorageLocation))
                    .map(this.baseStorageLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
