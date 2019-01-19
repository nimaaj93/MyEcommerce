package com.nimaaj.ecommerce.web.rest;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeResource {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> hello() {
        return productRepository.findAll();
    }

}
