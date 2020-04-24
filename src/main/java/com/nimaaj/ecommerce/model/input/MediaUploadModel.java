package com.nimaaj.ecommerce.model.input;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MediaUploadModel {

    @NotBlank
    private String caption;
    @NotNull
    private MultipartFile multipartFile;
    private List<Long> productIds;
    private boolean mainProductMedia;

}