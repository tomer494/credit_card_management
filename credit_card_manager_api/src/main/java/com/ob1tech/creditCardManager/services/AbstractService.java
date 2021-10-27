package com.ob1tech.creditCardManager.services;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.ob1tech.creditCardManager.cards.Card;
import com.ob1tech.creditCardManager.cards.CardsRepository;
import com.ob1tech.creditCardManager.model.APIRequest;
import com.ob1tech.creditCardManager.model.APIResponse;
import com.ob1tech.creditCardManager.transactions.Transaction;
import com.ob1tech.creditCardManager.transactions.TransactionService;
import com.ob1tech.creditCardManager.transactions.TransactionsRepository;
import com.ob1tech.creditCardManager.transactions.TransactionService.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Abstract service lays out the action proccess of all transactional services
 * T is the type of a unique request identifier for concurency assurence
 */
@Slf4j
@Service
public abstract class AbstractService<RQ extends APIRequest> {

    @Autowired
    private ActionFactory actionFactory;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private CardsRepository cardsRepository;

    private ConcurrentHashMap<Long, Object> cardsLock = new ConcurrentHashMap<>();

    protected void reportError(APIResponse response, String message) {
        response.setErrorMessage(message);
    }

    protected abstract boolean validRequest(RQ request, APIResponse response);

    public APIResponse execute(RQ request) {

        APIResponse response = actionFactory.getAPIResponse(request.getType());

        if (validRequest(request, response)) {

            try {
                Long cardId = Integer.valueOf(request.getCard_id()).longValue();
                cardsLock.putIfAbsent(cardId, new Object());
                synchronized (cardsLock.get(cardId)) {

                    boolean ok = false;

                    Optional<Card> cardOpt = cardsRepository.findById(cardId);
                    Card myCard = null;
                    if (!cardOpt.isPresent()) {
                        reportError(response, "Invalid card id");
                    } else {
                        myCard = cardOpt.get();
                        TransactionService.Builder builder = initTransaction(request, myCard);
                        myCard.setLast_active(new Date());

                        try {
                            ok = executeInnerAction(request, response, myCard);
                        } catch (Exception e) {
                            reportError(response, "Action failed: " + e.getMessage());
                            log.error("Action failed", e);
                        }
                        logTransaction(builder, response, ok);

                        Transaction transaction = builder.build();
                        cardsRepository.save(myCard);
                        transactionsRepository.save(transaction);
                    }

                    response.setOk(ok);
                }
            } catch (Exception e) {
                log.error("Unexpected error", e);
            }
        }

        return response;

    }

    protected TransactionService.Builder initTransaction(RQ request, Card myCard) {
        TransactionService.Builder builder = new TransactionService.Builder();
        builder.setType(request.getType());
        builder.setDate(new Date());
        if (myCard != null) {
            builder.copyCardData(myCard);
        }
        return builder;
    }

    private void logTransaction(TransactionService.Builder builder, APIResponse response, boolean ok) {
        fillTransactionData(builder, response, ok);
        Transaction transaction = builder.build();
        transactionsRepository.save(transaction);
    }

    protected abstract void fillTransactionData(Builder builder, APIResponse response, boolean ok);

    protected abstract boolean executeInnerAction(RQ request, APIResponse response, Card myCard);

}
