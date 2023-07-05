package com.example.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date-7/4/2023
 * Time-7:16 AM
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String helloWorld(){
        return "Hello World!";
    }
}
