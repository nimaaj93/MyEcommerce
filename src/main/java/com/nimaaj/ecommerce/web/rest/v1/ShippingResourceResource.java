package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ShippingResourceDTO;
import com.nimaaj.ecommerce.service.ShippingResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-resource")
@Validated
public class ShippingResourceResource {

    private final ShippingResourceService shippingResourceService;

    public ShippingResourceResource(ShippingResourceService shippingResourceService) {
        this.shippingResourceService = shippingResourceService;
    }

    @GetMapping
    public ResponseEntity<List<ShippingResourceDTO>> getShippingResourcesForRange(
            @RequestParam("fromDate") Long fromDate, @RequestParam("toDate") Long toDate) {
        return ResponseEntity.ok(shippingResourceService.get(fromDate, toDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingResourceDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shippingResourceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ShippingResourceDTO> create(
            @Validated({ ShippingResourceDTO.Create.class }) @RequestBody ShippingResourceDTO shippingResourceDTO) {
        return ResponseEntity.ok(shippingResourceService.add(shippingResourceDTO));
    }

    @PutMapping
    public ResponseEntity<ShippingResourceDTO> update(
            @Validated({ ShippingResourceDTO.Update.class }) @RequestBody ShippingResourceDTO shippingResourceDTO) {
        return ResponseEntity.ok(shippingResourceService.update(shippingResourceDTO));
    }

    @PatchMapping("/{id}/available/{available}")
    public ResponseEntity<ShippingResourceDTO> updateAvailable(@PathVariable("id") Long id,
                                                               @PathVariable("available") Integer available) {
        return ResponseEntity.ok(shippingResourceService.updateAvailable(id, available));
    }

    @PatchMapping("/{id}/total/{total}")
    public ResponseEntity<ShippingResourceDTO> updateTotal(@PathVariable("id") Long id,
                                                           @PathVariable("total") Integer total) {
        return ResponseEntity.ok(shippingResourceService.updateTotal(id, total));
    }

}