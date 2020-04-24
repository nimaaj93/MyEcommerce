package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ShippingResourceDto;

import java.util.List;

public interface ShippingResourceService {

    ShippingResourceDto getById(Long id);

    ShippingResourceDto update(ShippingResourceDto shippingResourceDTO);

    ShippingResourceDto updateAvailable(Long id, Integer available);

    ShippingResourceDto updateTotal(Long id, Integer total);

    List<ShippingResourceDto> get(Long fromDate, Long toDate);

    ShippingResourceDto add(ShippingResourceDto shippingResourceDTO);

}