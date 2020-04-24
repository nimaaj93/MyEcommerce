package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.MediaDto;
import com.nimaaj.ecommerce.model.input.MediaUploadModel;

import java.io.File;

public interface MediaService {

    MediaDto upload(MediaUploadModel model);

    MediaDto getById(Long id);

    File download(String uuid, String name);

    void delete(Long id);

}