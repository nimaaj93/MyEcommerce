package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.ProductStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by K550 VX on 27.10.2019.
 */
public class UpdateProductModel {

    @NotBlank
    private String titleEn;
    @NotBlank
    private String titleFa;
    @NotNull
    @Min(value = 0L)
    private Long price;
    @NotNull
    @Min(value = 0L)
    private Long manufacturerId;
    @NotNull
    @Min(value = 0L)
    private Integer stock;
    @NotNull
    private ProductStatus status;
    @NotNull
    @Min(value = 0L)
    private Long categoryId;
    private String details;

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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "UpdateProductModel{" +
                "titleEn='" + titleEn + '\'' +
                ", titleFa='" + titleFa + '\'' +
                ", price=" + price +
                ", manufacturerId=" + manufacturerId +
                ", stock=" + stock +
                ", status=" + status +
                ", categoryId=" + categoryId +
                ", details='" + details + '\'' +
                '}';
    }
}