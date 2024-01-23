package com.bank.user;

import jakarta.persistence.*;
import lombok.Data;
import com.bank.card.Card;
import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.*;

@Data
@Table(name="USERS")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=3, max=20, message="Имя должно содержать от 3 до 20 символов.")
    @Column(name = "username")
    private String username;
    
    @Size(min=5, max=20, message="Пароль долж содержать от 5 до 20 символов.")
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;
    
    public User() {}
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.cards = new ArrayList<>();
    }
}