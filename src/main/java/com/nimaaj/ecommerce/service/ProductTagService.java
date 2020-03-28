package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductTagRelDto;

public interface ProductTagService {

    ProductTagRelDto saveTagAndRelation(ProductTagRelDto productTagRelDto);

}
