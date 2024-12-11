package com.he180030.agentmanagement.service.impl;

import com.he180030.agentmanagement.service.AgentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AgentServiceImplTest {
    private final AgentService service = new AgentServiceImpl();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAgent() {
    }

    @Test
    void deleteAgent() {
    }

    @Test
    void updateAgent() {
    }

    @Test
    void findAllAgents() {
    }

    @ParameterizedTest
    @CsvSource({
            "agent1@gmail.com, Agent1",
            "agent2@gmail.com, Agent3"
    })
    void getAgentByEmail(String email, String expected) {
        try {
            if (email.contains("agent2")) {
                Assertions.assertNotEquals(expected, service.getAgentByEmail(email).getName());
            } else {
                Assertions.assertEquals(expected, service.getAgentByEmail(email).getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}