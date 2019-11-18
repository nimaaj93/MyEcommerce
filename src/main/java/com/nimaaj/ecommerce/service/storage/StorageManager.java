package com.nimaaj.ecommerce.service.storage;

import com.nimaaj.ecommerce.exception.UnknownSystemException;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;

@Component
public class StorageManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(StorageManager.class);

    private final EcommerceProperties ecommerceProperties;

    public StorageManager(EcommerceProperties ecommerceProperties) {
        this.ecommerceProperties = ecommerceProperties;
    }

    public void saveFile(String parentDirectory, MultipartFile multipartFile) {
        LOGGER.info("storing file in {} with name {}", parentDirectory, multipartFile.getOriginalFilename());
        try {
            Path filePath = Paths.get(ecommerceProperties.getStorage().getBasePath() +
                    System.getProperty("file.separator") +
                    parentDirectory +
                    System.getProperty("file.separator") +
                    multipartFile.getOriginalFilename());
            byte[] bytes = multipartFile.getBytes();
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, bytes);
        } catch (Exception e) {
            throw new UnknownSystemException(e);
        }
    }

    public File get(String parentDirectory, String name) {
        LOGGER.debug("get() run for parentDirectory {} and name {}", parentDirectory, name);
        return new File(ecommerceProperties.getStorage().getBasePath() +
                System.getProperty("file.separator") +
                parentDirectory +
                System.getProperty("file.separator") +
                name);
    }

    public void delete(String parentDirectory, String name) {
        LOGGER.debug("delete() run for parentDirectory {} and name {}", parentDirectory, name);
        File file = new File(ecommerceProperties.getStorage().getBasePath() +
                System.getProperty("file.separator") +
                parentDirectory +
                System.getProperty("file.separator") +
                name);
        if (file.exists()) {
            file.delete();
        }
    }

}