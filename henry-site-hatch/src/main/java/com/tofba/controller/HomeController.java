package com.tofba.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, Object> home() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "Hello World");
        return result;
    }
}
