package com.bank.card;

import com.bank.card.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.bank.user.User;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByNumber(int number);
    List<Card> findByUser(User user);
}
