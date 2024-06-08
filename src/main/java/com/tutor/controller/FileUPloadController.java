package com.tutor.controller;

import com.tutor.payload.FileUploadHelper;
import com.tutor.service.serviceImpl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/upload-file")
public class FileUPloadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @Autowired
    private FileService fileService;


    @PostMapping()
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getSize());
//        System.out.println(file.getContentType());
//        System.out.println(file.getName());
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must containst file");
            }
            if (file.getContentType().equals("image/jpg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Video contains only");
            }
            boolean f = fileUploadHelper.uploadFile(file);
            if (f){
                return ResponseEntity.ok("File is uploaded Successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong, please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public List<File> findAllFiles(@RequestParam("file") MultipartFile file){
        List<File> allFiles = fileService.getAllFilesInFolder(String.valueOf(file));
        return allFiles;
    }
}
