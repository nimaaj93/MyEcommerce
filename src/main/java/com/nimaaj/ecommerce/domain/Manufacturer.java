package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
public class Manufacturer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEn;
    private String titleFa;
    @Lob
    private String details;
    @Enumerated(EnumType.STRING)
    private ManufacturerStatus manufacturerStatus;
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ManufacturerStatus getManufacturerStatus() {
        return manufacturerStatus;
    }

    public void setManufacturerStatus(ManufacturerStatus manufacturerStatus) {
        this.manufacturerStatus = manufacturerStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
