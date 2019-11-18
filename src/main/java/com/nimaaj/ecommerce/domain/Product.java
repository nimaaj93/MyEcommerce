package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "product")
@NamedEntityGraph(name = "Product.gridview",
        attributeNodes = {
        @NamedAttributeNode(value = "productMediaRels",
                subgraph = "ProductMediaRel.full"),
        @NamedAttributeNode(value = "detail")})
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String titleEn;
    @NotNull
    private String titleFa;
    @NotNull
    private Long price;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @NotNull
    private Integer stock;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private ProductCategory category;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductMediaRel> productMediaRels;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private ProductDetail detail;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", titleFa='" + titleFa + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                ", stock=" + stock +
                ", status=" + status +
                ", category=" + category +
                ", productMediaRels=" + productMediaRels +
                ", detail=" + detail +
                ", containingBags=" + containingBags +
                '}';
    }
}