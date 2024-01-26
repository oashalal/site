package com.bank.user;

import lombok.Data;
import com.bank.card.Card;
import com.bank.role.Role;

import java.util.*;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

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
    
    @Size(min=5, message="Пароль должен содержать от 5 символов.")
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
    
    public User() {}
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.cards = new ArrayList<>();
    }
    
    public void addRole(Role role){
        roles.add(role);
    }
}