package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Media;
import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductMediaRel;
import com.nimaaj.ecommerce.exception.MediaNotFoundException;
import com.nimaaj.ecommerce.repository.MediaRepository;
import com.nimaaj.ecommerce.repository.ProductMediaRelRepository;
import com.nimaaj.ecommerce.service.ProductMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductMediaServiceImpl implements ProductMediaService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductMediaServiceImpl.class);

    private final ProductMediaRelRepository productMediaRelRepository;
    private final MediaRepository mediaRepository;

    public ProductMediaServiceImpl(ProductMediaRelRepository productMediaRelRepository, MediaRepository mediaRepository) {
        this.productMediaRelRepository = productMediaRelRepository;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Product addMediaIdsToNewProduct(Product product, List<Long> mediaIds) {
        LOGGER.debug("addMediaIdsToNewProduct() called for product {} and mediaIds {}", product, mediaIds);
        int order = 0;
        for (int i = 0 ; i < mediaIds.size() ; i++) {
            Media media = mediaRepository.findById(mediaIds.get(i))
                    .orElseThrow(MediaNotFoundException::new);
            ProductMediaRel productMediaRel = new ProductMediaRel();
            productMediaRel.setProduct(product);
            productMediaRel.setMedia(media);
            productMediaRel.setOrderVal(++order);
            if (i == 0) {
                productMediaRel.setMain(true);
            }
            productMediaRelRepository.save(productMediaRel);
        }
        return product;
    }

}