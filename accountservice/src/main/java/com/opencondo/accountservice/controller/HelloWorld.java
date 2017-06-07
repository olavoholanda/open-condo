package com.opencondo.accountservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Put a description of the class here.
 *
 * @author: Olavo Holanda
 */
@RestController
@RequestMapping("/")
public class HelloWorld {

    @RequestMapping(method = RequestMethod.GET)
    public String getGreeting() {
        return "Hello World";
    }
}
