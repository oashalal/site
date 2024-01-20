package com.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
    
    @GetMapping("/")
    public String mainPage(){
        return "redirect:/home";
    }
}