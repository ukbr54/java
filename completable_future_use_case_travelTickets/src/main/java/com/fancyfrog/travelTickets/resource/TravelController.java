package com.fancyfrog.travelTickets.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelController {

    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }
}
