package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.MediaStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "media")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "media_type")
public class Media extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private String basePath;
    private String host;
    @Enumerated(EnumType.STRING)
    private MediaStatus status;
    private Long size;
    private String format;
    private String caption;
    private String fileUuid;
    @OneToMany(mappedBy = "media")
    private List<ProductMediaRel> productMediaRels;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public List<ProductMediaRel> getProductMediaRels() {
        return productMediaRels;
    }

    public void setProductMediaRels(List<ProductMediaRel> productMediaRels) {
        this.productMediaRels = productMediaRels;
    }
}
