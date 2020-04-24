package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ShippingResourceDto;
import com.nimaaj.ecommerce.service.ShippingResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-resource")
@Validated
@RequiredArgsConstructor
public class ShippingResourceResource {

    private final ShippingResourceService shippingResourceService;

    @GetMapping
    public ResponseEntity<List<ShippingResourceDto>> getShippingResourcesForRange(
            @RequestParam("fromDate") Long fromDate, @RequestParam("toDate") Long toDate) {
        return ResponseEntity.ok(shippingResourceService.get(fromDate, toDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingResourceDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shippingResourceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ShippingResourceDto> create(
            @Validated({ ShippingResourceDto.Create.class }) @RequestBody ShippingResourceDto shippingResourceDTO) {
        return ResponseEntity.ok(shippingResourceService.add(shippingResourceDTO));
    }

    @PutMapping
    public ResponseEntity<ShippingResourceDto> update(
            @Validated({ ShippingResourceDto.Update.class }) @RequestBody ShippingResourceDto shippingResourceDTO) {
        return ResponseEntity.ok(shippingResourceService.update(shippingResourceDTO));
    }

    @PatchMapping("/{id}/available/{available}")
    public ResponseEntity<ShippingResourceDto> updateAvailable(@PathVariable("id") Long id,
                                                               @PathVariable("available") Integer available) {
        return ResponseEntity.ok(shippingResourceService.updateAvailable(id, available));
    }

    @PatchMapping("/{id}/total/{total}")
    public ResponseEntity<ShippingResourceDto> updateTotal(@PathVariable("id") Long id,
                                                           @PathVariable("total") Integer total) {
        return ResponseEntity.ok(shippingResourceService.updateTotal(id, total));
    }

}