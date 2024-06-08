package com.tutor.service.serviceImpl;

import com.tutor.entity.Files;
import com.tutor.payload.FilePayload;
import com.tutor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FilesService {

    @Autowired
    private FileRepository fileRepository;


    public String uploadFile(MultipartFile file) throws IOException {
        Files data = fileRepository.save(Files.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FilePayload.compressFile(file.getBytes())).build());
//              .fileData(file.getBytes()).build());
        if (data!=null){
            return "File Uploaded successfully : "+ file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadFile(String fileName){
        Optional<Files> dbFile = fileRepository.findByName(fileName);
        byte[] bytes = FilePayload.decompressFile(dbFile.get().getFileData());
        return bytes;
    }
}

