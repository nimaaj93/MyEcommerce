package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.DiscountCodeDto;
import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountCodeService {

    DiscountCodeDto create(DiscountCodeDto discountCodeDTO);

    DiscountCodeDto update(DiscountCodeDto discountCodeDTO);

    DiscountCodeDto updateState(Long id, DiscountCodeState state);

    Page<DiscountCodeDto> search(Pageable pageable, String query);

}