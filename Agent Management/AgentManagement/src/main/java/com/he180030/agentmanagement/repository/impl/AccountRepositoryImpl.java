package com.he180030.agentmanagement.repository.impl;

import com.he180030.agentmanagement.model.Account;
import com.he180030.agentmanagement.repository.AccountRepository;
import org.hibernate.Session;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private final Session session;

    public AccountRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void add(Account account) {
        session.merge(account);
    }

    @Override
    public void update(Account account) {
        session.merge(account);
    }

    @Override
    public void delete(Account account) {
        session.remove(account);
    }

    @Override
    public Account getAccount(long id) {
        return session.get(Account.class, id);
    }

    @Override
    public List<Account> getAccounts() {
        return session.createQuery("from Account", Account.class).getResultList();
    }

    @Override
    public Account getAccountByEmail(String email) {
        return session.createQuery("from Account where email = :email", Account.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<String> getEmails() {
        return session.createQuery("select email from Account ", String.class).getResultList();
    }
}
