package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ManufacturerDto;
import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;

public interface ManufacturerService {

    ManufacturerDto create(ManufacturerDto manufacturerDTO);

    ManufacturerDto update(ManufacturerDto manufacturerDTO);

    ManufacturerDto updateStatus(Long id, ManufacturerStatus status);

    ManufacturerDto getById(Long id);

}