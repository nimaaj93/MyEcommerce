package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.CustomerAddressDTO;
import com.nimaaj.ecommerce.service.CustomerAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/address")
public class CustomerAddressResource {

    private final CustomerAddressService customerAddressService;

    public CustomerAddressResource(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddressDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerAddressService.getCustomerAddress(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerAddressDTO>> getAllForCustomer() {
        return ResponseEntity.ok(customerAddressService.getCustomerAddresses());
    }

    @PostMapping
    public ResponseEntity<CustomerAddressDTO> create(@Validated(CustomerAddressDTO.Create.class)
                                                         @RequestBody CustomerAddressDTO customerAddressDTO) {
        return ResponseEntity.ok(customerAddressService.add(customerAddressDTO));
    }

    @PutMapping
    public ResponseEntity<CustomerAddressDTO> update(@Validated(CustomerAddressDTO.Update.class)
                                                     @RequestBody CustomerAddressDTO customerAddressDTO) {
        return ResponseEntity.ok(customerAddressService.update(customerAddressDTO));
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        customerAddressService.deleteCustomerAddress(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}