package com.nimaaj.ecommerce.util;

import com.nimaaj.ecommerce.enumaration.MediaType;
import com.nimaaj.ecommerce.exception.UnsupportedFileFormatException;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    private FileUtil() {
    }

    public static String getExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = "";

        int i = originalFilename.lastIndexOf('.');
        int p = Math.max(originalFilename.lastIndexOf('/'), originalFilename.lastIndexOf('\\'));

        if (i > p) {
            extension = originalFilename.substring(i+1);
        }
        return extension;
    }

    public static MediaType getMediaTypeFromFileExtension(String extension) {
        switch (extension.toLowerCase()) {
            case "jpg":
            case "jpeg":
            case "png":
                return MediaType.PHOTO;
            case "mp4":
            case "mkv":
                return MediaType.VIDEO;
            default:
                throw new UnsupportedFileFormatException();
        }
    }

}