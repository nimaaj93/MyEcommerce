package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.Product;

import java.util.List;

public interface ProductMediaService {

    Product addMediaIdsToNewProduct(Product product, List<Long> mediaIds);

}