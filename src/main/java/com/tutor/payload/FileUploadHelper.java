package com.tutor.payload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String UPLOAD_DIR="C:\\Users\\pk348\\OneDrive\\OneDrive\\Desktop\\psa\\Numetry\\Project\\Tutor - Copy\\Tutor\\src\\main\\resources\\static\\uploadedFiles";

    public boolean uploadFile(MultipartFile multipartFile){
        boolean f=false;

//
        try{
//            InputStream is = multipartFile.getInputStream();
//            byte[] data = new byte[is.available()];
//            is.read(data);
//
////Write
//            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "\\" + multipartFile.getOriginalFilename());
//            System.out.println(fos);
//            fos.write(data);
//            fos.flush();
//            fos.close();


            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
            f=true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }




}
