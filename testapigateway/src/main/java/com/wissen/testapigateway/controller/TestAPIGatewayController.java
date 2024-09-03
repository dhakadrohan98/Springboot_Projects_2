package com.wissen.testapigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class TestAPIGatewayController {

    @GetMapping("/wissen")
    public ResponseEntity<String> getAnonymus() {
        return ResponseEntity.ok("API gateway is working");
    }
}
