package com.he180030.agentmanagement.repository;

import com.he180030.agentmanagement.model.Agent;

import java.util.List;

public interface AgentRepository {
    void add(Agent agent);

    void delete(Agent agent);

    void update(Agent agent);

    List<Agent> findAll();

    Agent getByEmail(String email);
}
