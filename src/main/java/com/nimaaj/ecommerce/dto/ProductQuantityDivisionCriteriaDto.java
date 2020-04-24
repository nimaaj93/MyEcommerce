package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ProductQuantityDivisionCriteriaType;
import lombok.Data;

import java.util.TreeSet;

@Data
public class ProductQuantityDivisionCriteriaDto {

    private TreeSet<ProductQuantityDivisionCriteriaType> criteriaType = new TreeSet<>();

    private String criteriaValue;

}
