package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.util.db.converter.ProductDivisionCriteriaJsonConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;

@Entity
@Table(name = "product_quantity_division")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQuantityDivision extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Valid
    @Convert(converter = ProductDivisionCriteriaJsonConverter.class)
    private ArrayList<ProductQuantityDivisionCriteria> criteria;

    @PositiveOrZero
    @NotNull
    private Integer quantity;

    @ManyToOne
    private Product product;

}
