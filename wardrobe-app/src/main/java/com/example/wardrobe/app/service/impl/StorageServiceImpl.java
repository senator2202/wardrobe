package com.example.wardrobe.app.service.impl;

import com.example.wardrobe.app.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    public static final String STATIC_RESOURCES_DIR = System.getProperty("user.dir") + "/src/main/resources/static";
    public static final String RELATIVE_UPLOAD_DIRECTORY = "/images/product/";
    public static final String EMPTY_STRING = "";
    public static final String DOT = ".";

    @Override
    public String store(MultipartFile file) {
        String randomName = UUID.randomUUID().toString();
        String fileName = RELATIVE_UPLOAD_DIRECTORY + randomName +
                DOT + FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            file.transferTo(new File((STATIC_RESOURCES_DIR + fileName).replace("\\", "/")));
        } catch (Exception e) {
            return EMPTY_STRING;
        }
        return fileName;
    }
}
