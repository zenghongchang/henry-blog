package com.tofba.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {    
    @RequestMapping("/")
    public String clientIndex(Model model) {
        return "/client/index";
    }
}