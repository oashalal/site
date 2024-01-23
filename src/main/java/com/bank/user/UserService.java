package com.bank.user;

import com.bank.user.*;
import com.bank.card.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    public void addUser(String username, String password){
        if(!existsUser(username)){
            User user = new User(username, password);
            userRepository.save(user);
        }
    }
    
    public boolean existsUser(String username){
        return userRepository.existsByUsername(username);
    }
    
    public boolean addCard(String username, int cardNumber, String name){
        User user = getUser(username);
        if (user != null){
            Card card = new Card(0d, cardNumber, name);
            card.setUser(user);
            user.getCards().add(card);
            userRepository.save(user);
            cardRepository.save(card);
            return true;
        } else {
            return false;
        }
    }
    
    public User getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent()? user.get() : null;
    }
}