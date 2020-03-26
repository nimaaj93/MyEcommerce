package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.InvoiceRow;
import com.nimaaj.ecommerce.dto.InvoiceRowDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface InvoiceRowMapper extends CommonMapper<InvoiceRow, InvoiceRowDto> {

    @Override
    @Mappings({
            @Mapping(source = "invoice.id", target = "invoiceId")
    })
    InvoiceRowDto toDto(InvoiceRow entity);
}
