package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ApplicationDTO;
import com.nimaaj.ecommerce.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationResource {

    private final ApplicationService applicationService;

    public ApplicationResource(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<ApplicationDTO> getApp() {
        return ResponseEntity.ok(applicationService.getApplication());
    }

}