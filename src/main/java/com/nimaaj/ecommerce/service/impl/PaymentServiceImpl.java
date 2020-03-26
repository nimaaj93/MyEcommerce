package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.OrderPayment;
import com.nimaaj.ecommerce.dto.OrderPaymentDto;
import com.nimaaj.ecommerce.enumaration.PaymentStatus;
import com.nimaaj.ecommerce.exception.PaymentRequestNotFoundException;
import com.nimaaj.ecommerce.mapper.OrderPaymentMapper;
import com.nimaaj.ecommerce.repository.OrderPaymentRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final OrderPaymentRepository orderPaymentRepository;
    private final OrderPaymentMapper orderPaymentMapper;

    public PaymentServiceImpl(OrderPaymentRepository orderPaymentRepository,
                              OrderPaymentMapper orderPaymentMapper) {
        this.orderPaymentRepository = orderPaymentRepository;
        this.orderPaymentMapper = orderPaymentMapper;
    }

    @Override
    public void onSuccess(String requestId) {
        OrderPayment orderPayment = orderPaymentRepository.findByRequestId(requestId)
                .orElseThrow(PaymentRequestNotFoundException::new);

        orderPayment.setStatus(PaymentStatus.SUCCESSFUL);
        orderPaymentRepository.save(orderPayment);
    }

    @Override
    public void onFailure(String requestId) {
        OrderPayment orderPayment = orderPaymentRepository.findByRequestId(requestId)
                .orElseThrow(PaymentRequestNotFoundException::new);

        orderPayment.setStatus(PaymentStatus.FAILED);
        orderPaymentRepository.save(orderPayment);
    }

    @Override
    public OrderPaymentDto createPaymentRequest(OrderPaymentDto orderPaymentDto) {
        validateOrderPayment(orderPaymentDto);
        OrderPayment orderPayment = orderPaymentMapper.toEntity(orderPaymentDto);
        return orderPaymentMapper.toDto(orderPaymentRepository.save(orderPayment));
    }

    private void validateOrderPayment(OrderPaymentDto orderPaymentDto) {
        //TODO add validation
    }

    @Override
    public Page<OrderPaymentDto> getUserPaymentRequests(Pageable pageable) {
        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No userId found in token!"));
        return orderPaymentRepository.findAllByUser_Id(pageable, userId)
                .map(orderPaymentMapper::toDto);
    }

    @Override
    public Page<OrderPaymentDto> getAllPaymentRequests(Pageable pageable) {
        return orderPaymentRepository.findAll(pageable)
                .map(orderPaymentMapper::toDto);
    }
}
