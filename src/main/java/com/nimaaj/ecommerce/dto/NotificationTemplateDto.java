package com.nimaaj.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class NotificationTemplateDto {

    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    private Long id;

    @NotBlank(groups = { Create.class, Update.class })
    private String templateText;

    @NotBlank(groups = { Create.class })
    @Null(groups = Update.class)
    private String templateCode;

    // validation groups
    public class Create {
    }

    public class Update {
    }

}
