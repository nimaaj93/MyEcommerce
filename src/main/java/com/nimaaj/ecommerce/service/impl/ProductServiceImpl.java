package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.dto.FullProductDTO;
import com.nimaaj.ecommerce.dto.ProductDTO;
import com.nimaaj.ecommerce.mapper.ProductMapper;
import com.nimaaj.ecommerce.model.input.ProductFilterModel;
import com.nimaaj.ecommerce.repository.ProductRepository;
import com.nimaaj.ecommerce.service.ProductService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    @SuppressWarnings("unchecked")
    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository,
                              @Qualifier("productSearchSpecificationFactory")
                              SpecificationFactory productSpecificationFactory) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        // This is safe due to injection by qualifier
        this.productSpecificationFactory =
                (SpecificationFactory<Product, ProductFilterModel>)productSpecificationFactory;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        LOGGER.debug("getAllProducts() called");
        return productRepository.findAll()
                .stream().map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FullProductDTO> searchProducts(
            Pageable pageable, ProductFilterModel productFilterModel) {
        LOGGER.debug("searchProducts() called for pageable {} and filter {}", pageable, productFilterModel);
        Specification specification = productSpecificationFactory.getSpecification(productFilterModel);
        //TODO why this warning occurs??!!
        Page<Product> productPage = productRepository.findAll(specification, pageable);
        return productPage.map(productMapper::toFullProductDto);
    }

}
