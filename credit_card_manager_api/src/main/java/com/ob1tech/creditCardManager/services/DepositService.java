package com.ob1tech.creditCardManager.services;

import com.ob1tech.creditCardManager.cards.Card;
import com.ob1tech.creditCardManager.cards.CardsRepository;
import com.ob1tech.creditCardManager.model.Deposit;
import com.ob1tech.creditCardManager.model.DepositResponse;
import com.ob1tech.creditCardManager.transactions.Transaction;
import com.ob1tech.creditCardManager.transactions.TransactionService;
import com.ob1tech.creditCardManager.transactions.TransactionsRepository;
import com.ob1tech.creditCardManager.user.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DepositService {

    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UsersRepository usersRepository;

    private static ConcurrentHashMap<Integer, Object> cardsLock = new ConcurrentHashMap();


    public DepositResponse deposit(Deposit deposit){
        DepositResponse depositResponse = new DepositResponse();
        String error = null;
        boolean ok = false;
        boolean abort = false;
        if(deposit.getAmount() <= 0 ){
            error = "Illegal amount! Must deposit more then zero";
            abort = true;
        }

        if(!abort){

            try{
                cardsLock.putIfAbsent(deposit.getCard_id(), new Object());
                synchronized (cardsLock.get(deposit.getCard_id())) {
                    Optional<Card> cardOpt = cardsRepository.findById(deposit.getCard_id());
                    if (!cardOpt.isPresent()) {
                        error = "Invalid card id";
                        abort = true;
                    }
                    if (!abort) {
                        Card myCard = cardOpt.get();
                        if (myCard.isIssued() && myCard.isActive()) {

                            TransactionService.Builder builder = new TransactionService.Builder();
                            builder.setBalance(myCard.getBalance()).copyCardData(myCard);

                            final double newBalance = myCard.getBalance() + deposit.getAmount();
                            myCard.setBalance(newBalance);

                            Transaction transaction = builder.build();
                            transactionsRepository.save(transaction);
                            cardsRepository.save(myCard);
                            ok = true;
                        } else {
                            error = "Invalid card. either not issued yet or inactive!";
                            abort = true;
                        }
                    }
                }
            }catch(Exception e){
                log.error("Unexpected error", e);
                error = "Unexpected error: "+e.getMessage();
            }
        }


        if(error!=null){
            depositResponse.setErrorMessage("Illegal amount! Must deposit more then zero");
        }
        depositResponse.setOk(ok);

        return depositResponse;

    }

}
