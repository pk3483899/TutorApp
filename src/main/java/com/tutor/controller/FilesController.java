package com.tutor.controller;

import com.tutor.service.serviceImpl.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FilesService filesService;

//    http://localhost:8080/files
    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        String s = filesService.uploadFile(file);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(s);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadVideo(@PathVariable String fileName){
        byte[] bytes = filesService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("jpg/png"))
                .body(bytes);
    }
}
