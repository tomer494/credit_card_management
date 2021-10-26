package com.ob1tech.creditCardManager.user;

import com.ob1tech.creditCardManager.cards.Card;
import com.ob1tech.creditCardManager.transactions.Transaction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String phone;
    /**
     * Date of birth
     */
    @Getter
    @Setter
    private Date dob;
    /**
     * Id number or Passport or other unique identifier
     */
    @Getter
    @Setter
    private String id_number;
    @OneToMany
    @Getter
    @Setter
    private List<Card> cards;
    @OneToMany
    @Getter
    @Setter
    private List<Transaction> transactions;

}
