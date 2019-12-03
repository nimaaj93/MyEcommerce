package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.DiscountCodeDTO;
import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.service.DiscountCodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discount-code")
@Validated
public class DiscountCodeResource {

    private final DiscountCodeService discountCodeService;

    public DiscountCodeResource(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @PostMapping
    public ResponseEntity<DiscountCodeDTO> create(@Validated(DiscountCodeDTO.Create.class)
                                                  @RequestBody DiscountCodeDTO discountCodeDTO) {
        return ResponseEntity.ok(discountCodeService.create(discountCodeDTO));
    }

    @PutMapping
    public ResponseEntity<DiscountCodeDTO> update(@Validated(DiscountCodeDTO.Update.class)
                                                  @RequestBody DiscountCodeDTO discountCodeDTO) {
        return ResponseEntity.ok(discountCodeService.update(discountCodeDTO));
    }

    @PatchMapping("/{id}/state/{state}")
    public ResponseEntity<DiscountCodeDTO> updateState(@PathVariable("id") Long id,
                                                       @PathVariable("state") DiscountCodeState state) {
        return ResponseEntity.ok(discountCodeService.updateState(id,state));
    }

    @GetMapping
    public ResponseEntity<Page<DiscountCodeDTO>> search(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query) {
        return ResponseEntity.ok(discountCodeService.search(pageable, query));
    }

}