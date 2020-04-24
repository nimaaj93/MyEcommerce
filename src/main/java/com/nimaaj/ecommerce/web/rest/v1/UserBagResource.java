package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.UserBagDto;
import com.nimaaj.ecommerce.service.UserBagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-bag")
@RequiredArgsConstructor
public class UserBagResource {

    private final UserBagService userBagService;

    @GetMapping
    public ResponseEntity<List<UserBagDto>> getUserBag() {
        return ResponseEntity.ok(userBagService.getUserBag());
    }

    @PostMapping
    public ResponseEntity<List<UserBagDto>> addItem(@Validated @RequestBody UserBagDto userBagDTO) {
        return ResponseEntity.ok(userBagService.addItem(userBagDTO));
    }

    @PutMapping
    public ResponseEntity<List<UserBagDto>> updateItem(@Validated @RequestBody UserBagDto userBagDTO) {
        return ResponseEntity.ok(userBagService.updateItem(userBagDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserBagDto>> deleteItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userBagService.deleteItem(id));
    }

}