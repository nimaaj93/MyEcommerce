package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.CommentStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class ProductCommentDto extends BaseEntityDto {

    @Null(groups = Create.class)
    private Long id;

    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 511, groups = { Create.class, Update.class })
    private String comment;

    private Long userId;

    private Long productId;

    @Null(groups = Update.class)
    private Long parentId;

    private boolean edited;

    private boolean adminComment;

    @Null(groups = { Create.class, Update.class })
    private CommentStatus status;

    // validation groups
    public class Create {
    }

    public class Update {
    }

}
