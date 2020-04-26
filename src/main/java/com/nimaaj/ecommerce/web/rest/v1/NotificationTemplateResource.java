package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.NotificationTemplateDto;
import com.nimaaj.ecommerce.service.NotificationTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification-template")
@RequiredArgsConstructor
@Validated
public class NotificationTemplateResource {

    private final NotificationTemplateService notificationTemplateService;

    @GetMapping
    public ResponseEntity<Page<NotificationTemplateDto>> getTemplates(Pageable pageable,
                                                                      @RequestParam(value = "q", required = false)
                                                                      String query) {
        return ResponseEntity.ok(notificationTemplateService.getTemplates(pageable, query));
    }

    @PostMapping
    public ResponseEntity<NotificationTemplateDto> create(@Validated(NotificationTemplateDto.Create.class)
                                                          @RequestBody NotificationTemplateDto notificationTemplateDto) {
        return ResponseEntity.ok(notificationTemplateService.create(notificationTemplateDto));
    }

    @PutMapping
    public ResponseEntity<NotificationTemplateDto> update(@Validated(NotificationTemplateDto.Update.class)
                                                          @RequestBody NotificationTemplateDto notificationTemplateDto) {
        return ResponseEntity.ok(notificationTemplateService.update(notificationTemplateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        notificationTemplateService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
