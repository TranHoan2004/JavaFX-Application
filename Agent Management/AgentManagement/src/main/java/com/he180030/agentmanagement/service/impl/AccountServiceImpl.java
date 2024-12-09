package com.he180030.agentmanagement.service.impl;

import com.he180030.agentmanagement.dto.AccountDTO;
import com.he180030.agentmanagement.model.Account;
import com.he180030.agentmanagement.repository.AccountRepository;
import com.he180030.agentmanagement.repository.impl.AccountRepositoryImpl;
import com.he180030.agentmanagement.service.AccountService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl extends BaseService implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl() {
    }

    @Override
    public void addAccountDTO(AccountDTO accountDTO) {
        Session session = getSession();
        accountRepository = new AccountRepositoryImpl(getSession());

        Account account = accountRepository.getAccountByEmail(accountDTO.getEmail());
        try {
            if (account == null) {
                account.setEmail(accountDTO.getEmail());
                account.setPassword(accountDTO.getPassword());
                account.setName(accountDTO.getName());
                accountRepository.add(account);
                commitTransaction(session);
            } else {
                throw new Exception("This email already exists");
            }
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAccountDTO(AccountDTO account) {

    }

    @Override
    public void deleteAccountDTO(AccountDTO account) {

    }

    @Override
    public AccountDTO getAccountDTO(long id) {
        return null;
    }

    @Override
    public List<AccountDTO> getAccountDTOs() {
        return List.of();
    }

    @Override
    public AccountDTO getAccountDTOByEmail(String email) {
        return null;
    }

    @Override
    public List<String> getAllEmails() {
        return List.of();
    }
}
