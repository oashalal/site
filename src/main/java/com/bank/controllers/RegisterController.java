package com.bank.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bank.user.*;
import com.bank.card.CardService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
    
    @Autowired
    private CardService cardService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult, Model model) {
        if(userService.existsUser(user.getUsername())) {
            return "redirect:/register?error";                           
        }else if(bindingResult.hasErrors()){
            return "register";
        } else {
            userService.addUser(user.getUsername(), user.getPassword());
            return "redirect:/login";
        }                      
    }
}