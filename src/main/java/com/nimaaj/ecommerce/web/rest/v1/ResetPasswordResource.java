package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.model.ResetPasswordResult;
import com.nimaaj.ecommerce.model.input.ResetPassModel;
import com.nimaaj.ecommerce.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/reset-password")
@RequiredArgsConstructor
public class ResetPasswordResource {

    private final ResetPasswordService resetPasswordService;

    @PostMapping
    public ResponseEntity<ResetPasswordResult> createResetPassword(@RequestParam("to") String to) {
        return ResponseEntity.ok(resetPasswordService.resetPasswordRequest(to));
    }

    @GetMapping("/{code}/validate")
    public ResponseEntity<String> validate(@PathVariable("code") String code) {
        return ResponseEntity.ok(resetPasswordService.validate(code));
    }

    @PostMapping("/apply")
    public ResponseEntity<HttpStatus> consume(@Valid @RequestBody ResetPassModel model) {
        resetPasswordService.resetPass(model);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}