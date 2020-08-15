package com.tofba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(value = "homePage")
    public String getUserInfoById(Model model) {
        return "home-page";
    }
}
