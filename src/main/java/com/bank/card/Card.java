package com.bank.card;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.*;
import com.bank.user.User;

@Data
@Table(name="CARDS")
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=3, max=15, message="Имя должно содержать от 3 до 15 символов.")
    @Column(name = "name")
    private String name;
    
    @Column(name = "balance")
    private double balance;
    
    @Min(value=1000, message="Номер должен содеражать 4 цифры")
    @Max(value=9999, message="Номер должен содеражать 4 цифры")
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