package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.PaymentMethod;
import com.nimaaj.ecommerce.enumaration.PaymentProvider;
import com.nimaaj.ecommerce.enumaration.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPaymentDto extends BaseEntityDto {

    private Long id;
    private Long amount;
    private Long orderId;
    private PaymentStatus status;
    private PaymentMethod method;
    private PaymentProvider provider;
    private String requestId;
    private Long paymentDateTime;
    private Long userId;

}
