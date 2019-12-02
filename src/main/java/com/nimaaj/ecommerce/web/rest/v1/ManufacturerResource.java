package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ManufacturerDTO;
import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;
import com.nimaaj.ecommerce.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/manufacturer")
public class ManufacturerResource {

    private final ManufacturerService manufacturerService;

    public ManufacturerResource(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping
    public ResponseEntity<ManufacturerDTO> create(@Validated(ManufacturerDTO.Create.class)
                                                  @RequestBody ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.create(manufacturerDTO));
    }

    @PutMapping
    public ResponseEntity<ManufacturerDTO> update(@Validated(ManufacturerDTO.Update.class)
                                                  @RequestBody ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.update(manufacturerDTO));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<ManufacturerDTO> updateStatus(@PathVariable("id") Long id,
                                                        @PathVariable("status") ManufacturerStatus status) {
        return ResponseEntity.ok(manufacturerService.updateStatus(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(manufacturerService.getById(id));
    }

}