package com.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.bank.user.UserService;
import com.bank.card*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CardController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @GetMapping("/addcard")
    public String addCard(Model model) {
        model.addAttribute("card", new Card());
        return "add-card";
    }
    
    @PostMapping("/addcard")
    public String postAddCard(@AuthenticationPrincipal UserDetails userDetails, 
                              @Valid @ModelAttribute("card") Card card,
                              BindingResult bindingResult, Model model){
        if (!cardService.existsCard(card.getNumber())) {
            return "redirect:/addcard?error";
        } else if(bindingResult.hasErrors()) {
            return "add-card";
        }
        userService.addCard(userDetails.getUsername(), card.getNumber(), card.getName());
        return "redirect:profile";
    }
}