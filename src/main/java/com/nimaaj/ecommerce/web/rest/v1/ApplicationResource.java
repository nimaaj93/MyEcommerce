package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ApplicationDto;
import com.nimaaj.ecommerce.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationResource {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<ApplicationDto> getApp() {
        return ResponseEntity.ok(applicationService.getApplication());
    }

}