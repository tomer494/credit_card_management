package com.ob1tech.creditCardManager.transactions;

import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {

}
