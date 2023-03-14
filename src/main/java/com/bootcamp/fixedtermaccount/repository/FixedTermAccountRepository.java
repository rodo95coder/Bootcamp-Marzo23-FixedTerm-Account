package com.bootcamp.fixedtermaccount.repository;

import com.bootcamp.fixedtermaccount.model.FixedTermAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedTermAccountRepository extends MongoRepository<FixedTermAccount, String> {
    FixedTermAccount findFixedTermAccountByAccountNumberAndCustomerType(String accountNumber, String customerType);


}
