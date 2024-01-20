package com.bank.card;

import jakarta.persistence.*;
import lombok.Data;
import com.bank.user.User;

@Data
@Table(name="CARDS")
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "balance")
    private double balance;
    
    @Column(name = "number")
    private int number;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public Card(){}
    
    public Card(double balance, int number, String name){
        this.balance = balance;
        this.number = number;
        this.name = name;
    }
}