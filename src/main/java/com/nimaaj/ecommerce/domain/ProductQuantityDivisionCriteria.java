package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductQuantityDivisionCriteriaType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.TreeSet;

@Data
public class ProductQuantityDivisionCriteria {

    @NotEmpty
    private TreeSet<ProductQuantityDivisionCriteriaType> criteriaType = new TreeSet<>();

    @NotBlank
    private String criteriaValue;

}
