package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductTag;
import com.nimaaj.ecommerce.domain.ProductTagRel;
import com.nimaaj.ecommerce.dto.ProductTagRelDto;
import com.nimaaj.ecommerce.exception.InvalidInputDataException;
import com.nimaaj.ecommerce.exception.ProductNotFoundException;
import com.nimaaj.ecommerce.exception.ResourceNotFoundException;
import com.nimaaj.ecommerce.mapper.ProductTagRelMapper;
import com.nimaaj.ecommerce.repository.ProductRepository;
import com.nimaaj.ecommerce.repository.ProductTagRelRepository;
import com.nimaaj.ecommerce.repository.ProductTagRepository;
import com.nimaaj.ecommerce.service.ProductTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductTagServiceImpl implements ProductTagService {

    private final ProductTagRelRepository productTagRelRepository;
    private final ProductTagRepository productTagRepository;
    private final ProductTagRelMapper productTagRelMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductTagRelDto saveTagAndRelation(ProductTagRelDto productTagRelDto) {
        ProductTag productTag = saveAndGetProductTag(productTagRelDto);
        Product product = productRepository.findById(productTagRelDto.getId())
                .orElseThrow(ProductNotFoundException::new);
        ProductTagRel productTagRel = saveRelation(productTag, product);
        return productTagRelMapper.toDto(productTagRel);
    }

    private ProductTagRel saveRelation(ProductTag productTag, Product product) {

        return productTagRelRepository.findByProduct_IdAndProductTag_Id(product.getId(), productTag.getId())
                .orElseGet(() -> {
                    ProductTagRel productTagRel = new ProductTagRel();
                    productTagRel.setProduct(product);
                    productTagRel.setProductTag(productTag);
                    return productTagRelRepository.save(productTagRel);
                });
    }

    private ProductTag saveAndGetProductTag(ProductTagRelDto productTagRelDto) {

        Long id = productTagRelDto.getProductTag().getId();
        if (id != null) {
            return productTagRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        }
        String tagVal = productTagRelDto.getProductTag().getTagVal();
        if (!StringUtils.hasText(productTagRelDto.getProductTag().getTagVal())) {
            throw new InvalidInputDataException();
        }

        return productTagRepository.findByTagVal(tagVal).orElseGet(() -> {
            ProductTag newProductTag = new ProductTag();
            newProductTag.setTagVal(tagVal);
            return productTagRepository.save(newProductTag);
        });

    }

}
