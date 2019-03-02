package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.util.Empty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 3/3/2019.
 */
public interface CommonMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    default List<E> toEntity(List<D> dtoList) {
        if (Empty.isEmpty(dtoList)) {
            return null;
        }
        return dtoList.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    default List<D> toDto(List<E> entityList) {
        if (Empty.isEmpty(entityList)) {
            return null;
        }
        return entityList.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

}
