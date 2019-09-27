package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.dto.FullProductDTO;
import com.nimaaj.ecommerce.dto.ProductDTO;
import com.nimaaj.ecommerce.model.input.ProductFilterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by K550 VX on 3/3/2019.
 */
public interface ProductService {

    List<ProductDTO> getAllProducts();

    Page<FullProductDTO> searchProducts(Pageable pageable, ProductFilterModel productFilterModel);

}
