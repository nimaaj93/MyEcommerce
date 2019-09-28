package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.UserBagDTO;
import com.nimaaj.ecommerce.service.UserBagService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-bag")
public class UserBagResource {

    private final UserBagService userBagService;

    public UserBagResource(UserBagService userBagService) {
        this.userBagService = userBagService;
    }

    @GetMapping
    public ResponseEntity<List<UserBagDTO>> getUserBag() {
        return ResponseEntity.ok(userBagService.getUserBag());
    }

    @PostMapping
    public ResponseEntity<List<UserBagDTO>> addItem(@Validated @RequestBody UserBagDTO userBagDTO) {
        return ResponseEntity.ok(userBagService.addItem(userBagDTO));
    }

    @PutMapping
    public ResponseEntity<List<UserBagDTO>> updateItem(@Validated @RequestBody UserBagDTO userBagDTO) {
        return ResponseEntity.ok(userBagService.updateItem(userBagDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserBagDTO>> deleteItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userBagService.deleteItem(id));
    }

}