package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.UserDto;
import com.nimaaj.ecommerce.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-management")
@RequiredArgsConstructor
public class UserManagementResource {

    private final UserManagementService userManagementService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> pageUsers(Pageable pageable,
                                                   @RequestParam(value = "query", required = false) String query) {
        return ResponseEntity.ok(userManagementService.pageUsers(pageable, query));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<UserDto> enable(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userManagementService.enable(id));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<UserDto> disable(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userManagementService.disable(id));
    }

}