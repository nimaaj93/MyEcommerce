package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.service.OrderManagementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-management")
public class OrderManagementResource {

    private final OrderManagementService orderManagementService;

    public OrderManagementResource(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }



}
