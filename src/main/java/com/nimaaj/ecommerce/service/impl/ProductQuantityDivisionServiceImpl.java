package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductQuantityDivision;
import com.nimaaj.ecommerce.dto.ProductQuantityDivisionDto;
import com.nimaaj.ecommerce.exception.ProductNotFoundException;
import com.nimaaj.ecommerce.exception.ResourceNotFoundException;
import com.nimaaj.ecommerce.mapper.ProductQuantityDivisionMapper;
import com.nimaaj.ecommerce.repository.ProductQuantityDivisionRepository;
import com.nimaaj.ecommerce.repository.ProductRepository;
import com.nimaaj.ecommerce.service.ProductQuantityDivisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductQuantityDivisionServiceImpl implements ProductQuantityDivisionService {

    private final ProductQuantityDivisionRepository productQuantityDivisionRepository;
    private final ProductRepository productRepository;
    private final ProductQuantityDivisionMapper productQuantityDivisionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductQuantityDivisionDto> getQuantityDivisionsForProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return productQuantityDivisionMapper.toDto(productQuantityDivisionRepository.findAllByProduct_Id(productId));
    }

    @Override
    public ProductQuantityDivisionDto add(ProductQuantityDivisionDto productQuantityDivisionDto) {
        log.info("add() run for {}", productQuantityDivisionDto);
        Product product = productRepository.findById(productQuantityDivisionDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        ProductQuantityDivision productQuantityDivision = productQuantityDivisionMapper.toEntity(productQuantityDivisionDto);
        productQuantityDivision.setProduct(product);
        return productQuantityDivisionMapper.toDto(productQuantityDivisionRepository.save(productQuantityDivision));
    }

    @Override
    public ProductQuantityDivisionDto update(ProductQuantityDivisionDto productQuantityDivisionDto) {
        log.info("update() run for {}", productQuantityDivisionDto);
        productRepository.findById(productQuantityDivisionDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        ProductQuantityDivision productQuantityDivision = productQuantityDivisionRepository.findById(productQuantityDivisionDto.getId())
                .orElseThrow(ResourceNotFoundException::new);

        productQuantityDivisionMapper.toEntityForUpdate(productQuantityDivision, productQuantityDivisionDto);

        return productQuantityDivisionMapper.toDto(productQuantityDivisionRepository.save(productQuantityDivision));
    }

    @Override
    public void delete(Long productId, Long divisionId) {
        log.info("delete() run for productId {} and divisionId {} ", productId, divisionId);
        productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        productQuantityDivisionRepository.findById(divisionId)
                .ifPresent(productQuantityDivisionRepository::delete);
    }

    @Override
    public ProductQuantityDivisionDto updateQuantityForDivision(Long productId, Long divisionId, Integer newQuantity) {
        log.info("updateQuantityForDivision() run for productId {} ,divisionId {} and newQuantity {} ",
                productId, divisionId, newQuantity);
        ProductQuantityDivision productQuantityDivision = productQuantityDivisionRepository.findById(divisionId)
                .orElseThrow(ResourceNotFoundException::new);

        productQuantityDivision.setQuantity(newQuantity);

        return productQuantityDivisionMapper.toDto(productQuantityDivisionRepository.save(productQuantityDivision));
    }

    @Override
    public ProductQuantityDivisionDto increaseQuantityForDivision(Long productId, Long divisionId, Integer increment) {
        log.info("increaseQuantityForDivision() run for productId {} ,divisionId {} and increment {} ",
                productId, divisionId, increment);
        productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        productQuantityDivisionRepository.findById(divisionId).orElseThrow(ResourceNotFoundException::new);
        productQuantityDivisionRepository.increaseQuantity(divisionId, increment);
        return productQuantityDivisionMapper.toDto(
                productQuantityDivisionRepository.findById(divisionId).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public ProductQuantityDivisionDto decreaseQuantityForDivision(Long productId, Long divisionId, Integer decrement) {
        log.info("decreaseQuantityForDivision() run for productId {} ,divisionId {} and increment {} ",
                productId, divisionId, decrement);
        productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        productQuantityDivisionRepository.findById(divisionId).orElseThrow(ResourceNotFoundException::new);
        productQuantityDivisionRepository.decreaseQuantity(divisionId, decrement);
        return productQuantityDivisionMapper.toDto(
                productQuantityDivisionRepository.findById(divisionId).orElseThrow(ResourceNotFoundException::new));
    }
}

