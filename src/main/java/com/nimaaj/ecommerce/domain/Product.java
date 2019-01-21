package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String titleEn;
    private String titleFa;
    private Long price;
    @ManyToOne
    private Manufacturer manufacturer;
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne
    private ProductCategory category;
    @OneToMany(mappedBy = "product")
    private List<ProductMediaRel> productMediaRels;
    @OneToOne
    private ProductDetail detail;
    @OneToMany(mappedBy = "product")
    private List<UserBag> containingBags;

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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<ProductMediaRel> getProductMediaRels() {
        return productMediaRels;
    }

    public void setProductMediaRels(List<ProductMediaRel> productMediaRels) {
        this.productMediaRels = productMediaRels;
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public void setDetail(ProductDetail detail) {
        this.detail = detail;
    }

    public List<UserBag> getContainingBags() {
        return containingBags;
    }

    public void setContainingBags(List<UserBag> containingBags) {
        this.containingBags = containingBags;
    }
}
