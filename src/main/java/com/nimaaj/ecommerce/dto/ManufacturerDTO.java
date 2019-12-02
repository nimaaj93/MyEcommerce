package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class ManufacturerDTO {

    @NotNull(groups = { Update.class })
    @Null(groups = { Create.class })
    private Long id;
    @NotNull(groups = { Create.class, Update.class })
    private String titleEn;
    @NotNull(groups = { Create.class, Update.class })
    private String titleFa;
    private String details;
    @NotNull(groups = { Create.class, Update.class })
    private ManufacturerStatus manufacturerStatus;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ManufacturerStatus getManufacturerStatus() {
        return manufacturerStatus;
    }

    public void setManufacturerStatus(ManufacturerStatus manufacturerStatus) {
        this.manufacturerStatus = manufacturerStatus;
    }

    // validation groups
    public class Create {
    }

    public class Update {
    }

    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "id=" + id +
                ", titleEn='" + titleEn + '\'' +
                ", titleFa='" + titleFa + '\'' +
                ", details='" + details + '\'' +
                ", manufacturerStatus=" + manufacturerStatus +
                '}';
    }
}