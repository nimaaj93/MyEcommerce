package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ProductStatus;

import java.util.List;

/**
 * Created by K550 VX on 27.09.2019.
 */
public class ProductDto {

    private Long id;
    private String code;
    private String titleEn;
    private String titleFa;
    private Long price;
    private ProductStatus status;
    private Integer stock;

    private List<ProductMediaDTO> productMediaList;
    private ProductDetailDTO detail;
    private ProductCategoryDTO category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<ProductMediaDTO> getProductMediaList() {
        return productMediaList;
    }

    public void setProductMediaList(List<ProductMediaDTO> productMediaList) {
        this.productMediaList = productMediaList;
    }

    public ProductDetailDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDetailDTO detail) {
        this.detail = detail;
    }

    public ProductCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryDTO category) {
        this.category = category;
    }
}