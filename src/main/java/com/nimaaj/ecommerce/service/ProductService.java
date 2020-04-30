package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductDto;
import com.nimaaj.ecommerce.model.input.AddProductModel;
import com.nimaaj.ecommerce.model.input.ProductFilterModel;
import com.nimaaj.ecommerce.model.input.UpdateProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by K550 VX on 3/3/2019.
 */
public interface ProductService {

    List<ProductDto> getAllProducts();

    Page<ProductDto> searchProducts(Pageable pageable, ProductFilterModel productFilterModel);

    ProductDto addProduct(AddProductModel addProductModel);

    ProductDto getProductByCode(String code);

    ProductDto updateProduct(Long id, UpdateProductModel model);

    List<ProductDto> getProductsByIds(Set<Long> ids);

}
