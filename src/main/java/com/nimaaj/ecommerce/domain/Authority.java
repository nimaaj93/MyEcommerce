package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_user_authority")
@Data
@EqualsAndHashCode(callSuper = true)
public class Authority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @NotNull
    @NotBlank
    private String authorityVal;

}
