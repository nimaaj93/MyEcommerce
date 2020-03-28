package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.MediaStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "media")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "media_type")
@Data
@EqualsAndHashCode(callSuper = true)
public class Media extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String filePath;

    @NotBlank
    @NotNull
    private String basePath;

    private String host;

    @Enumerated(EnumType.STRING)
    private MediaStatus status;

    @NotNull
    private Long size;

    @NotBlank
    @NotNull
    private String format;

    private String caption;

    @NotBlank
    @NotNull
    private String fileUuid;

    @OneToMany(mappedBy = "media")
    private List<ProductMediaRel> productMediaRels;

}
