package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ShippingStatus;
import lombok.Data;

@Data
public class OrderShippingDto {

    private Long id;
    private Long orderId;
    private Long resourceId;
    private Long sendDateTime;
    private Long deliverDateTime;
    private ShippingStatus status;

}
