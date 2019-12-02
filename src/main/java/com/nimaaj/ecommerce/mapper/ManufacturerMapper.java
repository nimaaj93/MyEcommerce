package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Manufacturer;
import com.nimaaj.ecommerce.dto.ManufacturerDTO;
import org.mapstruct.Mapper;

/**
 * Created by K550 VX on 27.10.2019.
 */
@Mapper(componentModel = "spring")
public interface ManufacturerMapper extends CommonMapper<Manufacturer, ManufacturerDTO> {

    default Manufacturer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);
        return manufacturer;
    }

}