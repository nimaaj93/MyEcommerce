package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.UserDTO;
import com.nimaaj.ecommerce.service.UserManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-management")
public class UserManagementResource {

    private final UserManagementService userManagementService;

    public UserManagementResource(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> pageUsers(Pageable pageable,
                                                   @RequestParam(value = "query", required = false) String query) {
        return ResponseEntity.ok(userManagementService.pageUsers(pageable, query));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<UserDTO> enable(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userManagementService.enable(id));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<UserDTO> disable(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userManagementService.disable(id));
    }

}