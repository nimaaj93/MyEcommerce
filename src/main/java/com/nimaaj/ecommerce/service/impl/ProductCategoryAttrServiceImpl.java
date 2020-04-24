package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.ProductCategoryAttr;
import com.nimaaj.ecommerce.dto.ProductCategoryAttrDto;
import com.nimaaj.ecommerce.exception.ProductCategoryAttrNotFoundException;
import com.nimaaj.ecommerce.exception.ProductCategoryNotFoundException;
import com.nimaaj.ecommerce.mapper.ProductCategoryAttrMapper;
import com.nimaaj.ecommerce.repository.ProductCategoryAttrRepository;
import com.nimaaj.ecommerce.repository.ProductCategoryAttrValRepository;
import com.nimaaj.ecommerce.repository.ProductCategoryRepository;
import com.nimaaj.ecommerce.service.ProductCategoryAttrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCategoryAttrServiceImpl implements ProductCategoryAttrService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductCategoryAttrServiceImpl.class);

    private final ProductCategoryAttrRepository productCategoryAttrRepository;
    private final ProductCategoryAttrValRepository productCategoryAttrValRepository;
    private final ProductCategoryAttrMapper productCategoryAttrMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryAttrServiceImpl(ProductCategoryAttrRepository productCategoryAttrRepository,
                                          ProductCategoryAttrValRepository productCategoryAttrValRepository, ProductCategoryAttrMapper productCategoryAttrMapper, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryAttrRepository = productCategoryAttrRepository;
        this.productCategoryAttrValRepository = productCategoryAttrValRepository;
        this.productCategoryAttrMapper = productCategoryAttrMapper;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategoryAttrDto create(ProductCategoryAttrDto productCategoryAttrDTO) {
        LOGGER.debug("create() called for {}", productCategoryAttrDTO);
        productCategoryRepository.findById(productCategoryAttrDTO.getProductCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);
        ProductCategoryAttr entity = productCategoryAttrMapper.toEntity(productCategoryAttrDTO);
        return productCategoryAttrMapper.toDto(productCategoryAttrRepository.save(entity));
    }

    @Override
    public ProductCategoryAttrDto update(ProductCategoryAttrDto productCategoryAttrDTO) {
        LOGGER.debug("update() called for {}", productCategoryAttrDTO);
        productCategoryRepository.findById(productCategoryAttrDTO.getProductCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);
        productCategoryAttrRepository.findById(productCategoryAttrDTO.getId())
                .orElseThrow(ProductCategoryAttrNotFoundException::new);

        ProductCategoryAttr entity = productCategoryAttrMapper.toEntity(productCategoryAttrDTO);
        return productCategoryAttrMapper.toDto(productCategoryAttrRepository.save(entity));
    }

    @Override
    public List<ProductCategoryAttrDto> getAllByProductCategoryId(Long productCategoryId) {
        LOGGER.debug("getAllByProductCategoryId() called for {}", productCategoryId);
        productCategoryRepository.findById(productCategoryId)
                .orElseThrow(ProductCategoryNotFoundException::new);
        return productCategoryAttrRepository.findAllByProductCategory_Id(productCategoryId)
                .stream()
                .map(productCategoryAttrMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryAttrDto getById(Long id) {
        LOGGER.debug("getById() called for {}", id);
        return productCategoryAttrRepository.findById(id)
                .map(productCategoryAttrMapper::toDto)
                .orElseThrow(ProductCategoryAttrNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        LOGGER.debug("delete() called for {}", id);
        productCategoryAttrRepository.deleteById(id);
    }
}