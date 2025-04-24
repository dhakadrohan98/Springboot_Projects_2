package com.org.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fact")
public class FactorialController {

    @GetMapping("/{num}")
    private ResponseEntity<Long> getFactorial(@PathVariable("num") Long num) {
        System.out.println("request - " + num);
        Long res = 1l;
        for(int i = 1; i <= num; i++) {
            res = res * (long)i;
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
