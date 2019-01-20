package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

/**
 * Created by K550 VX on 1/19/2019.
 */
@Entity
@Table(name = "application")
public class Application extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleFa;
    private String titleEn;
    private boolean setupDone;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public boolean isSetupDone() {
        return setupDone;
    }

    public void setSetupDone(boolean setupDone) {
        this.setupDone = setupDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
