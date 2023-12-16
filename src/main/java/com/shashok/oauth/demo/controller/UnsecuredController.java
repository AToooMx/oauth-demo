package com.shashok.oauth.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured")
public class UnsecuredController {

    @GetMapping("/home")
    public String getHello(){
        return "hello";
    }
}
