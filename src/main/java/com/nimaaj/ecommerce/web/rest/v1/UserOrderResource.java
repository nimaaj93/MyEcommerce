package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.OrderDto;
import com.nimaaj.ecommerce.service.UserOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class UserOrderResource {

    private final UserOrderService userOrderService;

    public UserOrderResource(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto>> getUserOrders(Pageable pageable) {
        return ResponseEntity.ok(userOrderService.getUserOrders(pageable));
    }

}
