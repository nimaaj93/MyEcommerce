package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.OrderPaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    void onSuccess(String requestId);

    void onFailure(String requestId);

    OrderPaymentDto createPaymentRequest(OrderPaymentDto orderPaymentDto);

    Page<OrderPaymentDto> getUserPaymentRequests(Pageable pageable);

    Page<OrderPaymentDto> getAllPaymentRequests(Pageable pageable);

}
