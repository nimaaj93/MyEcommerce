package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.ProductSortType;

import java.util.List;

/**
 * Created by K550 VX on 3/4/2019.
 */
public class ProductFilterModel {

    private String searchQuery;
    private boolean inStockOnly;
    private Long categoryId;
    private List<Long> manufacturerIds;
    private Long priceFrom;
    private Long priceTo;
    private ProductSortType sortType;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public boolean isInStockOnly() {
        return inStockOnly;
    }

    public void setInStockOnly(boolean inStockOnly) {
        this.inStockOnly = inStockOnly;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<Long> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public Long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Long priceTo) {
        this.priceTo = priceTo;
    }

    public ProductSortType getSortType() {
        return sortType;
    }

    public void setSortType(ProductSortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public String toString() {
        return "ProductFilterModel{" +
                "searchQuery='" + searchQuery + '\'' +
                ", inStockOnly=" + inStockOnly +
                ", categoryId=" + categoryId +
                ", manufacturerIds=" + manufacturerIds +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", sortType=" + sortType +
                '}';
    }
}
