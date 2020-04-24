package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.CustomerAddressDto;
import com.nimaaj.ecommerce.service.CustomerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/address")
@RequiredArgsConstructor
public class CustomerAddressResource {

    private final CustomerAddressService customerAddressService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddressDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerAddressService.getCustomerAddress(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerAddressDto>> getAllForCustomer() {
        return ResponseEntity.ok(customerAddressService.getCustomerAddresses());
    }

    @PostMapping
    public ResponseEntity<CustomerAddressDto> create(@Validated(CustomerAddressDto.Create.class)
                                                         @RequestBody CustomerAddressDto customerAddressDTO) {
        return ResponseEntity.ok(customerAddressService.add(customerAddressDTO));
    }

    @PutMapping
    public ResponseEntity<CustomerAddressDto> update(@Validated(CustomerAddressDto.Update.class)
                                                     @RequestBody CustomerAddressDto customerAddressDTO) {
        return ResponseEntity.ok(customerAddressService.update(customerAddressDTO));
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        customerAddressService.deleteCustomerAddress(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}