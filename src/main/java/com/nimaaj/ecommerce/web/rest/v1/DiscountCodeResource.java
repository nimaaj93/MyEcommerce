package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.DiscountCodeDto;
import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.service.DiscountCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discount-code")
@Validated
@RequiredArgsConstructor
public class DiscountCodeResource {

    private final DiscountCodeService discountCodeService;

    @PostMapping
    public ResponseEntity<DiscountCodeDto> create(@Validated(DiscountCodeDto.Create.class)
                                                  @RequestBody DiscountCodeDto discountCodeDTO) {
        return ResponseEntity.ok(discountCodeService.create(discountCodeDTO));
    }

    @PutMapping
    public ResponseEntity<DiscountCodeDto> update(@Validated(DiscountCodeDto.Update.class)
                                                  @RequestBody DiscountCodeDto discountCodeDTO) {
        return ResponseEntity.ok(discountCodeService.update(discountCodeDTO));
    }

    @PatchMapping("/{id}/state/{state}")
    public ResponseEntity<DiscountCodeDto> updateState(@PathVariable("id") Long id,
                                                       @PathVariable("state") DiscountCodeState state) {
        return ResponseEntity.ok(discountCodeService.updateState(id,state));
    }

    @GetMapping
    public ResponseEntity<Page<DiscountCodeDto>> search(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query) {
        return ResponseEntity.ok(discountCodeService.search(pageable, query));
    }

}