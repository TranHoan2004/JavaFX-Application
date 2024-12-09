package com.he180030.agentmanagement.service;

import com.he180030.agentmanagement.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    void addAccountDTO(AccountDTO account);

    void updateAccountDTO(AccountDTO account);

    void deleteAccountDTO(AccountDTO account);

    AccountDTO getAccountDTOByID(long id);

    List<AccountDTO> getAccountDTOs();

    AccountDTO getAccountDTOByEmail(String email);

    List<String> getAllEmails();
}
