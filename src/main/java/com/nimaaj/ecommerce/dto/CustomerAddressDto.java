package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.AddressStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class CustomerAddressDto {

    @NotNull(groups = { Update.class })
    @Null(groups = { Create.class })
    private Long id;
    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 40, groups = { Create.class, Update.class })
    private String city;
    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 256, groups = { Create.class, Update.class })
    private String addressVal;
    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 20, groups = { Create.class, Update.class })
    private String postCode;
    private boolean defaultSelected;
    @Null(groups = { Create.class, Update.class })
    private AddressStatus addressStatus;
    @Null(groups = { Create.class, Update.class })
    private Long customerId;

    // validation groups
    public class Create {
    }
    public class Update {
    }

}