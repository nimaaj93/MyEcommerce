package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "product_comment")
public class ProductComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @OneToOne
    private User user;
    @ManyToOne
    private Product product;
    @ManyToOne
    private ProductComment parent;
    private boolean edited;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductComment getParent() {
        return parent;
    }

    public void setParent(ProductComment parent) {
        this.parent = parent;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
