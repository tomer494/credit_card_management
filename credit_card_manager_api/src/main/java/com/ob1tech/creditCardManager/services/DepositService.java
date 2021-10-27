package com.ob1tech.creditCardManager.services;

import com.ob1tech.creditCardManager.cards.Card;
import com.ob1tech.creditCardManager.model.APIResponse;
import com.ob1tech.creditCardManager.model.Deposit;
import com.ob1tech.creditCardManager.model.DepositResponse;
import com.ob1tech.creditCardManager.transactions.TransactionService.Builder;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * This service handels a deposit action
 */
@Service
public class DepositService extends AbstractService<Deposit> {

    @Override
    protected boolean validRequest(Deposit request, APIResponse response) {
        if (request.getAmount() <= 0) {
            reportError(response, "Illegal amount! Must deposit more then zero");
            return false;
        }
        return true;
    }

    @Override
    protected void fillTransactionData(Builder builder, APIResponse response, boolean ok) {
        builder.setBalance(((DepositResponse) response).getBalance());

    }

    @Override
    protected boolean executeInnerAction(Deposit request, APIResponse response, Card myCard) {
        final double newBalance = myCard.getBalance() + request.getAmount();
        myCard.setBalance(newBalance);
        ((DepositResponse) response).setBalance(newBalance);
        return true;
    }

}
