package com.he180030.agentmanagement.service;

import com.he180030.agentmanagement.dto.AgentDTO;

import java.util.List;

public interface AgentService {
    void addAgent(AgentDTO agent) throws Exception;

    void deleteAgent(AgentDTO agent) throws Exception;

    void updateAgent(AgentDTO agent) throws Exception;

    List<AgentDTO> findAllAgents() throws Exception;

    AgentDTO getAgentByEmail(String email) throws Exception;
}
