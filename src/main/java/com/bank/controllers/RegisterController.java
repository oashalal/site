package com.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.bank.user.UserService;
import com.bank.card.CardService;
import org.springframework.ui.Model;


@Controller
public class RegisterController {
    
    @Autowired
    private CardService cardService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }
    
    @PostMapping("/register")
    public String registration(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("password2") String password2) {
        if(validForm(username, password, password2)){
            userService.addUser(username, password);
            return "redirect:/login";                           
        }else{
            return "redirect:/register?error";
        }                       
    }
    public boolean validForm(String username, String password, String password2){
        if(password.length() >= 3 &&
           username.length() >= 4 &&
           password.equals(password2) &&
           !(userService.hasUser(username))) return true;
        return false;
    }
}