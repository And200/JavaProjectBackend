package com.example.example.service.image.storage;


import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private static final Logger logger= LoggerFactory.getLogger(FilesController.class);
    private  final StorageService storageService;
    @GetMapping(value = "/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource>serveFile(@PathVariable String filename, HttpServletRequest request){
        Resource file=storageService.loadAsResource(filename);
        String contentType=null;
        try{
            contentType=request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        }catch (IOException e){
            logger.info("could not determinate file...");
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
    }

}
