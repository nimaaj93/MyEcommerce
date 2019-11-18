package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.MediaDTO;
import com.nimaaj.ecommerce.model.input.MediaUploadModel;

import java.io.File;

public interface MediaService {

    MediaDTO upload(MediaUploadModel model);

    MediaDTO getById(Long id);

    File download(String uuid, String name);

    void delete(Long id);

}