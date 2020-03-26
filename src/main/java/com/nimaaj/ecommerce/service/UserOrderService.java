package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.OrderDto;
import com.nimaaj.ecommerce.model.input.OrderSubmitModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserOrderService {

    void submitOrder(OrderSubmitModel orderSubmitModel);

    Page<OrderDto> getUserOrders(Pageable pageable);

}
