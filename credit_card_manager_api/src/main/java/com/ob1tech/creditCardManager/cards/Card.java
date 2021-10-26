package com.ob1tech.creditCardManager.cards;

import com.ob1tech.creditCardManager.transactions.Transaction;
import com.ob1tech.creditCardManager.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    @Getter @Setter private Long id;
    @ManyToOne
    @Getter @Setter private User user;
    @Getter @Setter private String card_number;
    @Getter @Setter private Date issue_date;
    @Getter @Setter private Date last_active;
    @Getter @Setter private boolean issued;
    @Getter @Setter private boolean active;
    @Getter @Setter private double balance;
    @Getter @Setter private double credit;

    @OneToMany(targetEntity=Transaction.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Transaction> transactions;
}
