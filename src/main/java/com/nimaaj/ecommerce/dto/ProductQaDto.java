package com.nimaaj.ecommerce.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQaDto extends BaseEntityDto {

    private Long id;
    private String question;
    private String answer;
    private UserDTO askUser;
    private UserDTO replyUser;
    private Long replyDateTime;
    private Long productId;

}
