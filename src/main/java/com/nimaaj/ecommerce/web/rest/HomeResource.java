package com.nimaaj.ecommerce.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeResource {

    @GetMapping
    public String hello() {
        return "hello";
    }

}
