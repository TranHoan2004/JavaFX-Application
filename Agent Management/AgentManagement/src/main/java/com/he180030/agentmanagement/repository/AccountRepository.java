package com.he180030.agentmanagement.repository;

import com.he180030.agentmanagement.model.Account;

import java.util.List;

public interface AccountRepository {
    void add(Account account);

    void update(Account account);

    void delete(Account account);

    Account getAccount(long id);

    List<Account> getAccounts();

    Account getAccountByEmail(String email);

    List<String> getEmails();
}
