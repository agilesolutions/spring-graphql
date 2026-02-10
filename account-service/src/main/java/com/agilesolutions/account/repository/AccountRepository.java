package com.agilesolutions.account.repository;

import com.agilesolutions.account.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    @Override
    List<Account> findAll();

    List<Account> findByClientIdIn(List<Long> clientIds);

    @Query("{number:'?0'}")
    List<Account> findAccountByNumber(String number);

}
