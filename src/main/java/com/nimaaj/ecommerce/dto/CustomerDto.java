package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.CustomerType;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;
    private String customerName;
    private CustomerType customerType;

}