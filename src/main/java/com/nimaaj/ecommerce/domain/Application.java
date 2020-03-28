package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by K550 VX on 1/19/2019.
 */
@Entity
@Table(name = "application")
@Data
@EqualsAndHashCode(callSuper = true)
public class Application extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @NotBlank
    private String titleFa;

    @NotNull
    @NotBlank
    private String titleEn;

    private boolean setupDone;

    private String description;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 5)
    private String productCodePrefix;

}
