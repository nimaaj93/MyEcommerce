package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductDetail;
import com.nimaaj.ecommerce.dto.FullProductDTO;
import com.nimaaj.ecommerce.dto.ProductDTO;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final SpecificationFactory<Product, ProductFilterModel> productSpecificationFactory;
    private final ProductDetailRepository productDetailRepository;
    private final ProductMediaService productMediaService;

    @SuppressWarnings("unchecked")
    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository,
                              @Qualifier("productSearchSpecificationFactory")
                                      SpecificationFactory productSpecificationFactory,
                              ProductDetailRepository productDetailRepository,
                              ProductMediaService productMediaService) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        // This is safe due to injection by qualifier
        this.productSpecificationFactory =
                (SpecificationFactory<Product, ProductFilterModel>)productSpecificationFactory;
        this.productDetailRepository = productDetailRepository;
        this.productMediaService = productMediaService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        LOGGER.debug("getAllProducts() called");
        return productRepository.findAll()
                .stream().map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<FullProductDTO> searchProducts(
            Pageable pageable, ProductFilterModel productFilterModel) {
        LOGGER.debug("searchProducts() called for pageable {} and filter {}", pageable, productFilterModel);
        Specification specification = productSpecificationFactory.getSpecification(productFilterModel);
        //TODO why this warning occurs??!!
        Page<Product> productPage = productRepository.findAll(specification, pageable);
        return productPage.map(productMapper::toFullProductDto);
    }

    @Override
    @Transactional
    public FullProductDTO addProduct(AddProductModel addProductModel) {
        LOGGER.debug("addProduct() called for addProductModel {}", addProductModel);
        validateAddProduct(addProductModel);
        Product product = productMapper.toEntity(addProductModel);
        product = productRepository.save(product);
        product = addProductDetail(addProductModel, product);
        if (!CollectionUtils.isEmpty(addProductModel.getMediaIds())) {
            product = productMediaService.addMediaIdsToNewProduct(product, addProductModel.getMediaIds());
        }
        return productMapper.toFullProductDto(product);
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
    public FullProductDTO getProductByCode(String code) {
        LOGGER.debug("getProductByCode() called for code {}", code);
        return productRepository.findByCode(code)
                .map(productMapper::toFullProductDto)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transactional
    public FullProductDTO updateProduct(Long id, UpdateProductModel model) {
        LOGGER.debug("updateProduct() called for {}", model);
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product = productRepository.save(product);

        return productMapper.toFullProductDto(product);
    }
}