package com.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Map<String, String>> getProducts() {
        System.out.println("inside product controller");
        return List.of(
                Map.of("id", "i1", "name", "Product A"),
                Map.of("id", "i2", "name", "Product B")
        );
    }
}
