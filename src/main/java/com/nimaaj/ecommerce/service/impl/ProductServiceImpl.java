package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductDetail;
import com.nimaaj.ecommerce.dto.ProductDto;
import com.nimaaj.ecommerce.exception.DuplicateProductCodeException;
import com.nimaaj.ecommerce.exception.ProductNotFoundException;
import com.nimaaj.ecommerce.mapper.ProductMapper;
import com.nimaaj.ecommerce.model.input.AddProductModel;
import com.nimaaj.ecommerce.model.input.ProductFilterModel;
import com.nimaaj.ecommerce.model.input.UpdateProductModel;
import com.nimaaj.ecommerce.repository.ProductDetailRepository;
import com.nimaaj.ecommerce.repository.ProductRepository;
import com.nimaaj.ecommerce.service.ProductMediaService;
import com.nimaaj.ecommerce.service.ProductService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 3/3/2019.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final SpecificationFactory<Product, ProductFilterModel> productSpecificationFactory;
    private final ProductDetailRepository productDetailRepository;
    private final ProductMediaService productMediaService;

    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository,
                              @Qualifier("productSearchSpecificationFactory")
                                          SpecificationFactory<Product, ProductFilterModel> productSpecificationFactory,
                              ProductDetailRepository productDetailRepository,
                              ProductMediaService productMediaService) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.productSpecificationFactory = productSpecificationFactory;
        this.productDetailRepository = productDetailRepository;
        this.productMediaService = productMediaService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        LOGGER.debug("getAllProducts() called");
        return productRepository.findAll()
                .stream().map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<ProductDto> searchProducts(
            Pageable pageable, ProductFilterModel productFilterModel) {
        LOGGER.debug("searchProducts() called for pageable {} and filter {}", pageable, productFilterModel);
        Specification specification = productSpecificationFactory.getSpecification(productFilterModel);
        //TODO why this warning occurs??!!
        Page<Product> productPage = productRepository.findAll(specification, pageable);
        return productPage.map(productMapper::toDto);
    }

    @Override
    @Transactional
    public ProductDto addProduct(AddProductModel addProductModel) {
        LOGGER.debug("addProduct() called for addProductModel {}", addProductModel);
        //TODO generate product code
        validateAddProduct(addProductModel);
        Product product = productMapper.toEntity(addProductModel);
        product = productRepository.save(product);
        product = addProductDetail(addProductModel, product);
        if (!CollectionUtils.isEmpty(addProductModel.getMediaIds())) {
            product = productMediaService.addMediaIdsToNewProduct(product, addProductModel.getMediaIds());
        }
        return productMapper.toDto(product);
    }

    private void validateAddProduct(AddProductModel addProductModel) {
        productRepository.findByCode(addProductModel.getCode())
                .ifPresent(product -> {
                    throw new DuplicateProductCodeException();
                });
    }

    private Product addProductDetail(AddProductModel addProductModel, Product product) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDetails(addProductModel.getDetails());
        productDetail = productDetailRepository.save(productDetail);
        product.setDetail(productDetail);
        product = productRepository.save(product);
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductByCode(String code) {
        LOGGER.debug("getProductByCode() called for code {}", code);
        return productRepository.findByCode(code)
                .map(productMapper::toDto)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, UpdateProductModel model) {
        LOGGER.debug("updateProduct() called for {}", model);
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product = productRepository.save(product);

        return productMapper.toDto(product);
    }
}