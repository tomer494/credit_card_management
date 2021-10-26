package com.ob1tech.creditCardManager.transactions;

import com.ob1tech.creditCardManager.cards.Card;
import com.ob1tech.creditCardManager.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Card card;
    @ManyToOne
    @Getter
    @Setter
    private User user;
    /**
     * TODO - Allow managment actions on acount
     * private User acting_user;
     */
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private double init_balance;
    @Getter
    @Setter
    private double balance;
    @Getter
    @Setter
    private double init_credit;
    @Getter
    @Setter
    private double credit;
    @Getter
    @Setter
    private Date date;
}
