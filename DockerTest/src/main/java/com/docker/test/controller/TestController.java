package com.docker.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TestController {

    @RequestMapping("/")
    public Map<String, Object> getValues() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Java api is working fine");
        data.put("lanuages", Arrays.asList("Java", "Python", "Javascript"));
        data.put("code", 2345);
        Arrays.sort(new int[]{1});
        return data;
    }
}
