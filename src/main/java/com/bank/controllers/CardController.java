package com.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.bank.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class CardController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/addcard")
    public String addCard(Model model) {
        return "add-card";
    }
    
    @PostMapping("/addcard")
    public String postAddCard(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String name,
            @RequestParam String number, Model model){
        int num;
        try{
            num = Integer.parseInt(number);
        } catch(Exception ec){
            return "redirect:/addcard?error";
        }
        if (num >= 1000 && num <=9999){
            userService.addCard(userDetails.getUsername(), num, name);
        }
        else return "redirect:/addcard?error";
        return "redirect:/profile";
    }
}