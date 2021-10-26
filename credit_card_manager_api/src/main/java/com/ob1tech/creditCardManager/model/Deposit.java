package com.ob1tech.creditCardManager.model;

import lombok.Getter;
import lombok.Setter;

public class Deposit extends APIRequest{

    @Getter @Setter private int user_id;
    @Getter @Setter private int card_id;

    /**
     * Amount to be deposited in credit card acount
     */
    @Getter @Setter private double amount;

}
