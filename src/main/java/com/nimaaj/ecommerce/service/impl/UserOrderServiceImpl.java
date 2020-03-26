package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.UserBag;
import com.nimaaj.ecommerce.dto.OrderDto;
import com.nimaaj.ecommerce.exception.UserBagItemOutOfStockException;
import com.nimaaj.ecommerce.mapper.OrderMapper;
import com.nimaaj.ecommerce.model.input.OrderSubmitModel;
import com.nimaaj.ecommerce.repository.OrderRepository;
import com.nimaaj.ecommerce.repository.UserBagRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.UserOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    private final UserBagRepository userBagRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public UserOrderServiceImpl(UserBagRepository userBagRepository,
                                OrderRepository orderRepository,
                                OrderMapper orderMapper) {
        this.userBagRepository = userBagRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void submitOrder(OrderSubmitModel orderSubmitModel) {
        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No userId found in token!"));

        List<UserBag> userBagList = userBagRepository.findByUser_id(userId);
        validateUserBagList(userBagList);

        //TODO complete steps

    }

    private void validateUserBagList(List<UserBag> userBagList) {
        for (UserBag userBag : userBagList) {
            if (userBag.getCount() > userBag.getProduct().getStock()) {
                throw new UserBagItemOutOfStockException();
            }
        }
    }

    @Override
    public Page<OrderDto> getUserOrders(Pageable pageable) {

        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No userId found in token!"));

        return orderRepository.findAllByUser_Id(pageable, userId)
                .map(orderMapper::toDto);
    }
}
