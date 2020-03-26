package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/redirect/success")
    public ResponseEntity<HttpStatus> redirectPaymentSuccess(@RequestParam("requestId") String requestId) {
        paymentService.onSuccess(requestId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/redirect/failure")
    public ResponseEntity<HttpStatus> redirectPaymentFailure(@RequestParam("requestId") String requestId) {
        paymentService.onFailure(requestId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
