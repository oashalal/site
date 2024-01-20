package com.bank.user;

import jakarta.persistence.*;
import lombok.Data;
import com.bank.card.Card;
import java.util.List;
import java.util.ArrayList;

@Data
@Table(name="USERS")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;
    
    public User(){
    	
    }
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.cards = new ArrayList<>();
    }
}