package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.SaleDto;
import com.nimaaj.ecommerce.model.input.SaleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SaleService {

    SaleDto add(SaleDto saleDto);

    SaleDto update(SaleDto saleDto);

    SaleDto getById(SaleDto saleDto);

    SaleDto activate(Long id);

    SaleDto deactivate(Long id);

    Page<SaleDto> pageSales(Pageable pageable, SaleFilter saleFilter);

    List<SaleDto> getAllActiveSalesForCurrentDateTime();

}
