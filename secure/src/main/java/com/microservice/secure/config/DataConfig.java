package com.microservice.secure.config;

import com.microservice.secure.entity.Account;
import com.microservice.secure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataConfig implements ApplicationRunner {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account("string","string","string");
        accountRepository.save(account);
        // bug here
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Account index = new Account();
            index.setUsername("username" + i);
            index.setFirstName("first_name" + i);
            index.setLastName("last_name" + i);
        }
        accountRepository.saveAll(accounts);
    }
}