package com.bank.card;

import com.bank.card.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bank.user.*;
import java.util.Optional;
import java.util.List;

@Service
public class CardService {
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public boolean hasCard(int number){
        Card card = cardRepository.findByNumber(number);
        if (card != null){
            return true;
        }
        return false;
    }
    
    public List<Card> getUserCards(String username){
        User user = userRepository.findByUsername(username).get();
        return cardRepository.findByUser(user);
    }
}