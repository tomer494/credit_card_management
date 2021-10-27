package com.ob1tech.creditCardManager.services;

import com.ob1tech.creditCardManager.model.APIResponse;
import com.ob1tech.creditCardManager.model.DepositResponse;
import com.ob1tech.creditCardManager.transactions.Transaction;
import com.ob1tech.creditCardManager.transactions.TransactionService;

import org.springframework.stereotype.Component;

@Component
public class ActionFactory {

    public APIResponse getAPIResponse(String type) {
        APIResponse response = null;
        switch (type) {
        case "DEPOSIT":
            response = new DepositResponse();
            break;
        default:
            break;
        }
        return response;
    }

}
