package com.bank.card;

import com.bank.card.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bank.user.*;
import java.util.Optional;
import java.util.List;
import com.bank.card.*;

@Service
public class CardService {
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private UserService userService;
    
    public boolean existsCard(int number){
        return cardRepository.existsByNumber(number);
    }
    
    public List<Card> getUserCards(String username){
        User user = userService.get(username).get();
        return cardRepository.findByUser(user);
    }
}