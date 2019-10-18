package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Media;
import com.nimaaj.ecommerce.domain.Photo;
import com.nimaaj.ecommerce.domain.Video;
import com.nimaaj.ecommerce.dto.MediaDTO;
import com.nimaaj.ecommerce.enumaration.MediaType;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Mapper(componentModel = "spring")
public interface MediaMapper extends CommonMapper<Media, MediaDTO> {

    @BeforeMapping
    default void setMediaType(Media entity, @MappingTarget MediaDTO dto) {
        if (entity instanceof Photo) {
            dto.setMediaType(MediaType.PHOTO);
        } else if (entity instanceof Video) {
            dto.setMediaType(MediaType.VIDEO);
        }
    }

}