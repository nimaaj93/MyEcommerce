package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ManufacturerDTO;
import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;

public interface ManufacturerService {

    ManufacturerDTO create(ManufacturerDTO manufacturerDTO);

    ManufacturerDTO update(ManufacturerDTO manufacturerDTO);

    ManufacturerDTO updateStatus(Long id, ManufacturerStatus status);

    ManufacturerDTO getById(Long id);

}