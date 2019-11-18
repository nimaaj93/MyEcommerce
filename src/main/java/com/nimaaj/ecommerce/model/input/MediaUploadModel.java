package com.nimaaj.ecommerce.model.input;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MediaUploadModel {

    @NotBlank
    private String caption;
    @NotNull
    private MultipartFile multipartFile;
    private List<Long> productIds;
    private boolean mainProductMedia;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public boolean isMainProductMedia() {
        return mainProductMedia;
    }

    public void setMainProductMedia(boolean mainProductMedia) {
        this.mainProductMedia = mainProductMedia;
    }

    @Override
    public String toString() {
        return "MediaUploadModel{" +
                "caption='" + caption + '\'' +
                ", multipartFile=" + multipartFile +
                ", productIds=" + productIds +
                ", mainProductMedia=" + mainProductMedia +
                '}';
    }
}