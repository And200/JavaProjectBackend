package com.example.example.service.impl.storage;

import com.example.example.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileSystemStorageImpl implements StorageService {


    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;
    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation=Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to Store empty file");

            }
            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        }catch (IOException e){
            throw new RuntimeException("Failed To Store File");
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
       try {
           Path file = rootLocation.resolve(filename);
           Resource resource = new UrlResource((file.toUri()));

           if (resource.exists() || resource.isReadable()) {
               return resource;
           } else {
               throw new RuntimeException("could not read file" + filename);
           }

       }catch (MalformedURLException e){
            throw new RuntimeException("could not read file"+filename);
       }
    }
}
