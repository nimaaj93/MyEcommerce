package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.ProductSortType;
import lombok.Data;

import java.util.List;

/**
 * Created by K550 VX on 3/4/2019.
 */
@Data
public class ProductFilterModel {

    private String searchQuery;
    private boolean inStockOnly;
    private Long categoryId;
    private List<Long> manufacturerIds;
    private Long priceFrom;
    private Long priceTo;
    private ProductSortType sortType;

}
