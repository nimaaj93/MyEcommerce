package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Media;
import com.nimaaj.ecommerce.dto.MediaDTO;
import org.mapstruct.Mapper;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Mapper(componentModel = "spring")
public interface MediaMapper extends CommonMapper<Media, MediaDTO> {

}