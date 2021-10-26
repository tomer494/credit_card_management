package com.ob1tech.creditCardManager.model;

import lombok.Getter;
import lombok.Setter;

public class DepositResponse extends APIResponse{

    @Getter @Setter private double balance;
}
