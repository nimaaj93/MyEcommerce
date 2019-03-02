package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.dto.ProductDTO;

import java.util.List;

/**
 * Created by K550 VX on 3/3/2019.
 */
public interface ProductService {

    List<ProductDTO> getAllProducts();

}
