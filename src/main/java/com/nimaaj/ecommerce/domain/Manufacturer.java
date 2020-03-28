package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "manufacturer")
@Data
@EqualsAndHashCode(callSuper = true)
public class Manufacturer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String titleEn;

    @NotNull
    @NotBlank
    private String titleFa;

    @Lob
    private String details;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ManufacturerStatus manufacturerStatus;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

}
