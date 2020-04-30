package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.enumaration.SaleType;
import lombok.Data;

@Data
public class SaleFilter {

    private String query;
    private SaleType type;
    private SaleStatus status;

}
