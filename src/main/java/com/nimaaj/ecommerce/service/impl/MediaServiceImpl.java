package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.*;
import com.nimaaj.ecommerce.dto.MediaDTO;
import com.nimaaj.ecommerce.enumaration.MediaStatus;
import com.nimaaj.ecommerce.enumaration.MediaType;
import com.nimaaj.ecommerce.exception.MediaNotFoundException;
import com.nimaaj.ecommerce.mapper.MediaMapper;
import com.nimaaj.ecommerce.model.input.MediaUploadModel;
import com.nimaaj.ecommerce.repository.MediaRepository;
import com.nimaaj.ecommerce.repository.ProductMediaRelRepository;
import com.nimaaj.ecommerce.service.MediaService;
import com.nimaaj.ecommerce.service.storage.StorageManager;
import com.nimaaj.ecommerce.util.FileUtil;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MediaServiceImpl implements MediaService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MediaServiceImpl.class);

    private final StorageManager storageManager;
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final EcommerceProperties ecommerceProperties;
    private final ProductMediaRelRepository productMediaRelRepository;

    public MediaServiceImpl(StorageManager storageManager,
                            MediaRepository mediaRepository,
                            MediaMapper mediaMapper,
                            EcommerceProperties ecommerceProperties,
                            ProductMediaRelRepository productMediaRelRepository) {
        this.storageManager = storageManager;
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.ecommerceProperties = ecommerceProperties;
        this.productMediaRelRepository = productMediaRelRepository;
    }

    @Override
    public MediaDTO upload(MediaUploadModel model) {
        LOGGER.debug("upload() called for {}", model);
        MediaDTO mediaDTO;
        String extension = FileUtil.getExtension(model.getMultipartFile());
        MediaType mediaType = FileUtil.getMediaTypeFromFileExtension(extension);
        if (mediaType == MediaType.PHOTO) {
            mediaDTO = savePhoto(model, extension);
        } else {
            mediaDTO = saveVideo(model, extension);
        }
        storageManager.saveFile(mediaDTO.getBasePath(), model.getMultipartFile());
        return mediaDTO;
    }

    private MediaDTO saveVideo(MediaUploadModel model, String extension) {
        Video video = new Video();
        return saveMedia(video,  model, extension);
    }

    private MediaDTO savePhoto(MediaUploadModel model, String extension) {
        Photo photo = new Photo();
        return saveMedia(photo,  model, extension);
    }

    private MediaDTO saveMedia(Media media, MediaUploadModel model, String extension) {
        String uuid = UUID.randomUUID().toString();
        media.setStatus(MediaStatus.STORED);
        media.setSize(model.getMultipartFile().getSize());
        media.setBasePath(uuid);
        media.setFileUuid(uuid);
        media.setFilePath(model.getMultipartFile().getOriginalFilename());
        media.setCaption(model.getCaption());
        media.setFormat(extension);

        media = mediaRepository.save(media);

        if (!CollectionUtils.isEmpty(model.getProductIds())) {
            for (Long productId : model.getProductIds()) {
                ProductMediaRel productMediaRel = new ProductMediaRel();
                productMediaRel.setMedia(media);
                Product product = new Product();
                product.setId(productId);
                productMediaRel.setProduct(product);
                if (model.isMainProductMedia()) {
                    Optional<ProductMediaRel> productMainMedia =
                            productMediaRelRepository.findByProduct_IdAndMainTrue(productId);
                    if (productMainMedia.isPresent()) {
                        ProductMediaRel productMainMediaEntity = productMainMedia.get();
                        productMainMediaEntity.setMain(false);
                        productMediaRelRepository.save(productMainMediaEntity);
                    }
                    productMediaRel.setMain(true);
                }
                int maxOrderVal = productMediaRelRepository.findTopByProduct_IdOrderByOrderVal(productId)
                                                        .map(ProductMediaRel::getOrderVal)
                                                        .orElse(0);
                productMediaRel.setOrderVal(maxOrderVal + 1);
                productMediaRelRepository.save(productMediaRel);
            }
        }

        return mediaMapper.toDto(media);
    }

    @Override
    public MediaDTO getById(Long id) {
        LOGGER.debug("getById() called for {}", id);
        return mediaRepository.findById(id)
                    .filter(media -> media.getStatus() != MediaStatus.DELETED)
                    .map(mediaMapper::toDto)
                    .orElseThrow(MediaNotFoundException::new);
    }

    @Override
    public File download(String uuid, String name) {
        LOGGER.debug("getById() called for uuid {} and name {}", uuid, name);
        return storageManager.get(uuid, name);
    }

    @Override
    public void delete(Long id) {
        LOGGER.debug("delete() called for {}", id);
        mediaRepository.findById(id)
                .map(media -> {
                    media.setStatus(MediaStatus.DELETED);
                    mediaRepository.save(media);
                    storageManager.delete(media.getBasePath(), media.getFilePath());
                    return media;
                })
                .orElseThrow(MediaNotFoundException::new);
    }
}