package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ManufacturerDto;
import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;
import com.nimaaj.ecommerce.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerResource {

    private final ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<ManufacturerDto> create(@Validated(ManufacturerDto.Create.class)
                                                  @RequestBody ManufacturerDto manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.create(manufacturerDTO));
    }

    @PutMapping
    public ResponseEntity<ManufacturerDto> update(@Validated(ManufacturerDto.Update.class)
                                                  @RequestBody ManufacturerDto manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.update(manufacturerDTO));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<ManufacturerDto> updateStatus(@PathVariable("id") Long id,
                                                        @PathVariable("status") ManufacturerStatus status) {
        return ResponseEntity.ok(manufacturerService.updateStatus(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(manufacturerService.getById(id));
    }

}