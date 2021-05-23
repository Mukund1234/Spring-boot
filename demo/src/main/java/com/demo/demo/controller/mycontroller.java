package com.demo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mycontroller {
    
    @GetMapping("/home")
    public String home(){
        return "this is home page";
    }
}
