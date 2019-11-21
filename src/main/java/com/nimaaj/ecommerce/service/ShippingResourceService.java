package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ShippingResourceDTO;

import java.util.List;

public interface ShippingResourceService {

    ShippingResourceDTO getById(Long id);

    ShippingResourceDTO update(ShippingResourceDTO shippingResourceDTO);

    ShippingResourceDTO updateAvailable(Long id, Integer available);

    ShippingResourceDTO updateTotal(Long id, Integer total);

    List<ShippingResourceDTO> get(Long fromDate, Long toDate);

    ShippingResourceDTO add(ShippingResourceDTO shippingResourceDTO);

}