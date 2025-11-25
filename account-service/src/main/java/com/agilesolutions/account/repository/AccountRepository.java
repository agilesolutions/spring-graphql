package com.agilesolutions.account.repository;

import com.agilesolutions.account.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AccountRepository extends ListCrudRepository<Account, String> {

    @Override
    List<Account> findAll();
}
