package com.nimaaj.ecommerce.dto;

/**
 * Created by K550 VX on 27.09.2019.
 */
public class ProductDetailDTO {

    private Long id;
    private Long productId;
    private String details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}