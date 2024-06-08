package com.tutor.service.serviceImpl;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public List<File> getAllFilesInFolder(String folderPath) {
        List<File> fileList = new ArrayList<>();
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            // Handle if the folder doesn't exist or is not a directory
            System.err.println("Error: Invalid folder path.");
            return fileList;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                }
            }
        } else {
            // Handle if the folder is empty or cannot be read
            System.err.println("Error: Unable to access folder contents.");
        }

        return fileList;
    }

    // You can add more methods here for additional functionalities related to file operations
}
