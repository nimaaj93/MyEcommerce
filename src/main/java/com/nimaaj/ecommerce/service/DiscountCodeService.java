package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.DiscountCodeDTO;
import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountCodeService {

    DiscountCodeDTO create(DiscountCodeDTO discountCodeDTO);

    DiscountCodeDTO update(DiscountCodeDTO discountCodeDTO);

    DiscountCodeDTO updateState(Long id, DiscountCodeState state);

    Page<DiscountCodeDTO> search(Pageable pageable, String query);

}