package com.ai.book.bootrestbook.controllers;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ai.book.bootrestbook.helper.FileUploadHelper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FileUploadContoller {
    @Autowired
    private FileUploadHelper fileUploadHelper;
    private Logger log= Logger.getLogger(FileUploadContoller.class.getName());;

    @PostMapping("/upload-file")   
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("must contain file");
        }
        if (!file.getContentType().equals("image/jpeg")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg allowed");
        }

        //upload file
        
          boolean f=  fileUploadHelper.uploadFile(file);
          if(f){
            //return ResponseEntity.ok("File is succesfully uploaded");
            return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            // http://localhost:8080/image/P.jpeg
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("testing");
    }
}
