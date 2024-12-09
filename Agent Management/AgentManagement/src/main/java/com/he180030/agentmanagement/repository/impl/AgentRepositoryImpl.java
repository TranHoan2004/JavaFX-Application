package com.he180030.agentmanagement.repository.impl;

import com.he180030.agentmanagement.model.Agent;
import com.he180030.agentmanagement.repository.AgentRepository;
import org.hibernate.Session;

import java.util.List;

public class AgentRepositoryImpl implements AgentRepository {
    private final Session session;

    public AgentRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void add(Agent agent) {
        session.merge(agent);
    }

    @Override
    public void delete(Agent agent) {
        session.remove(agent);
    }

    @Override
    public void update(Agent agent) {
        session.merge(agent);
    }

    @Override
    public List<Agent> findAll() {
        return session.createQuery("from Agent", Agent.class).getResultList();
    }

    @Override
    public Agent getByEmail(String email) {
        return session.createQuery("from Agent where email = :email", Agent.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
