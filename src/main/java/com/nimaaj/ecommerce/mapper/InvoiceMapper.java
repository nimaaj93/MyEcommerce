package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Invoice;
import com.nimaaj.ecommerce.dto.InvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { InvoiceRowMapper.class })
public interface InvoiceMapper extends CommonMapper<Invoice, InvoiceDto> {

    @Override
    @Mappings({
            @Mapping(source = "order.id", target = "orderId")
    })
    InvoiceDto toDto(Invoice entity);
}
