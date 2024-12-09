package com.he180030.agentmanagement.service.impl;

import com.he180030.agentmanagement.dto.AccountDTO;
import com.he180030.agentmanagement.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AccountServiceImplTest {
    private final AccountService accountService;

    public AccountServiceImplTest() {
        this.accountService = new AccountServiceImpl();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAccountDTO() {

    }

    @Test
    void updateAccountDTO() {
    }

    @Test
    void deleteAccountDTO() {
    }

    @ParameterizedTest
    @CsvSource({
            "1, tester1",
            "2, tester2",
            "3, tester4"
    })
    void getAccountDTOByID(long id, String expected) {
        try {
            AccountDTO accountDTO = accountService.getAccountDTOByID(id);
            Assertions.assertNotNull(accountDTO);
            if (id == 3) {
                Assertions.assertNotEquals(expected, accountDTO.getName());
            } else {
                Assertions.assertEquals(expected, accountDTO.getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getAccountDTOByEmail() {
    }

    @Test
    void getAllEmails() {
    }

    @Test
    void isAccountExisted() {
    }

    @Test
    void getAccountDTOs() {
        try {
            Assertions.assertNotNull(accountService.getAccountDTOs());
            Assertions.assertEquals(4, accountService.getAccountDTOs().size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}