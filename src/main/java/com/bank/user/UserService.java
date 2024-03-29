package com.bank.user;

import com.bank.user.*;
import com.bank.card.*;
import com.bank.role.*;

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
    
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private Role USER;
    
    public UserService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        Optional<Role> user = roleRepository.findByName("USER");
        if (user.isPresent()) {
            this.USER = user.get();
        } else {
            Role role = new Role("USER");
            roleRepository.save(role);
            this.USER = role;
        }
    }
    
    public void addUser(String username, String password){
        if(!existsUser(username)){
            User user = new User(username, passwordEncoder.encode(password));
            user.addRole(USER);
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
        return new MyUserDetails(user);
    }
}