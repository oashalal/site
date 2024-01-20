package com.bank.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.bank.user.UserService;
import java.util.List;
import com.bank.card.*;

@Controller
public class ProfileController{
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CardService cardService;
    
    @GetMapping("/profile")
    public String homeProfile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        
        List<Card> cards = cardService.getUserCards(username);
        model.addAttribute("cards", cards);
        return "profile";
    }
}