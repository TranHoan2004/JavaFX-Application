package com.he180030.agentmanagement.service.impl;

import com.he180030.agentmanagement.dto.AccountDTO;
import com.he180030.agentmanagement.model.Account;
import com.he180030.agentmanagement.repository.AccountRepository;
import com.he180030.agentmanagement.repository.impl.AccountRepositoryImpl;
import com.he180030.agentmanagement.service.AccountService;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl extends BaseService implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public void addAccountDTO(AccountDTO accountDTO) {
        Session session = initialize();

        Account account = convert(accountDTO);
        try {
            beginTransaction(session);
            accountRepository.add(account);
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAccountDTO(AccountDTO accountDTO) {
        Session session = initialize();

        Account account = convert(accountDTO);
        try {
            beginTransaction(session);
            accountRepository.update(account);
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAccountDTO(AccountDTO accountDTO) {
        Session session = initialize();

        Account account = convert(accountDTO);
        try {
            beginTransaction(session);
            accountRepository.delete(account);
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public AccountDTO getAccountDTOByID(long id) {
        Session session = initialize();

        Account account = new Account();
        try {
            beginTransaction(session);
            account = accountRepository.getAccount(id);
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
        return convert(account);
    }

    @Override
    public List<AccountDTO> getAccountDTOs() {
        Session session = initialize();

        List<AccountDTO> list = new ArrayList<>();
        try {
            beginTransaction(session);
            for (Account account: accountRepository.getAccounts()) {
                list.add(convert(account));
            }
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public AccountDTO getAccountDTOByEmail(String email) {
        Session session = initialize();

        Account account = new Account();
        try {
            beginTransaction(session);
            account = accountRepository.getAccountByEmail(email);
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
        return convert(account);
    }

    @Override
    public List<String> getAllEmails() {
        Session session = initialize();

        List<String> list = new ArrayList<>();
        try {
            beginTransaction(session);
            list.addAll(accountRepository.getEmails());
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    public boolean isAccountExisted(AccountDTO accountDTO) {
        Session session = initialize();
        try {
            beginTransaction(session);
            Account account = accountRepository.getAccountByEmail(accountDTO.getEmail());
            if (account != null) {
                return true;
            }
            commitTransaction(session);
        } catch (Exception e) {
            rollbackTransaction(session);
            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.ALL, e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    private Session initialize() {
        Session session = getSession();
        accountRepository = new AccountRepositoryImpl(getSession());
        return session;
    }

    @NotNull
    private Account convert(@NotNull AccountDTO accountDTO) {
        Account account = new Account();
        account.setName(accountDTO.getName());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        return account;
    }

    @NotNull
    private AccountDTO convert(@NotNull Account account) {
        return AccountDTO.builder()
                .email(account.getEmail())
                .password(account.getPassword())
                .name(account.getName())
                .build();
    }
}
