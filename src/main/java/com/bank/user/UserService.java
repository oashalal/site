package com.bank.user;

import com.bank.user.*;
import com.bank.card.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public void addUser(String username, String password){
        if(!existsUser(username)){
            User user = new User(username, passwordEncoder.encode(password));
            userRepository.save(user);
        }
    }
    
    public boolean existsUser(String username){
        return userRepository.existsByUsername(username);
    }
    
    public boolean addCard(String username, int number, String name){
        User user = getUser(username);
        if (user != null){
            Card card = new Card(0d, number, name);
            card.setUser(user);
            cardRepository.save(card);
            user.getCards().add(card);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
    
    public User getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent()? user.get() : null;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                   .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}