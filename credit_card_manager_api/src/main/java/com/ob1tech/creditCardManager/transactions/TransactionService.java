package com.ob1tech.creditCardManager.transactions;

import com.ob1tech.creditCardManager.cards.Card;
import com.ob1tech.creditCardManager.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TransactionService {

    public static enum TransactionType {
        DEPOSIT, WITHDRAW, PAY, BLOCK
    }

    public static class Builder {

        private Card card;
        private User user;
        /**
         * TODO - Allow managment actions on acount
         * private User acting_user;
         */
        private String type;
        private String description;
        private double init_balance;
        private double balance;
        private double init_credit;
        private double credit;
        private Date date;

        public Builder copyCardData(Card card) {
            this.card = card;
            this.init_balance = card.getBalance();
            this.balance = card.getBalance();
            this.init_credit = card.getCredit();
            this.credit = card.getCredit();
            this.user = card.getUser();
            return this;
        }

        public Builder setCardData(Card card) {
            this.card = card;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setInitBalance(double init_balance) {
            this.init_balance = init_balance;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setInitCredit(double init_credit) {
            this.init_credit = init_credit;
            return this;
        }

        public Builder setCredit(double credit) {
            this.credit = credit;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.setCard(this.card);
            transaction.setType(this.type);
            transaction.setDescription(this.description);
            transaction.setInit_balance(this.init_balance);
            transaction.setBalance(this.balance);
            transaction.setInit_credit(this.init_credit);
            transaction.setCredit(this.credit);
            if (this.date == null) {
                this.date = new Date();
            }
            transaction.setDate(this.date);
            transaction.setUser(this.user);
            return transaction;
        }

    }
}
