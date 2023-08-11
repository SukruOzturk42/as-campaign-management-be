package com.anadolusigorta.campaignmanagement.infrastructure.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String test(){
        return "WELCOME CAMPUS";
    }
}
