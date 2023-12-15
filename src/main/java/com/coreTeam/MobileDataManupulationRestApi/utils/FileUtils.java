package com.coreTeam.MobileDataManupulationRestApi.utils;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileUtils {



    public static void deletePhoto(MobileModel mobile){
        String pathname="src/main/resources/static/images/mobileimage/";
        String photo=""+mobile.getId()+mobile.getDateCreated()+".png";
        try {
            Files.delete(Paths.get(pathname,photo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void uploadFile(MobileModel savedMobile,String imageName) throws IOException {
        savedMobile.setImage("/owner-api/owener/mobile/productphoto/"+savedMobile.getId()+savedMobile.getDateCreated()+".png");
        byte[] decodedBytes= Base64.getDecoder().decode(imageName);
        Files.write(Path.of("src/main/resources/static/images/mobileimage/" + savedMobile.getId()+savedMobile.getDateCreated()+".png"), decodedBytes);
    }

}
