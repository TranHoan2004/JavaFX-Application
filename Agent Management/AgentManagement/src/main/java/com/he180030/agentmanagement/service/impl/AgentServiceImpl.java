package com.he180030.agentmanagement.service.impl;

import com.he180030.agentmanagement.dto.AgentDTO;
import com.he180030.agentmanagement.model.Agent;
import com.he180030.agentmanagement.repository.AgentRepository;
import com.he180030.agentmanagement.repository.impl.AgentRepositoryImpl;
import com.he180030.agentmanagement.service.AgentService;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AgentServiceImpl extends BaseService implements AgentService {
    private AgentRepository agentRepository;
    private Session session;

    @Override
    public void addAgent(AgentDTO agentDTO) throws Exception {
        try {
            initialize();
            Agent agent = convert(agentDTO);
            beginTransaction(session);
            isExisted(agentDTO);
            agentRepository.add(agent);
            commitTransaction(session);
        } catch (Exception e) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            rollbackTransaction(session);
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAgent(AgentDTO agentDTO) throws Exception {
        try {
            initialize();
            beginTransaction(session);
            Agent ag = agentRepository.getByEmail(agentDTO.getEmail());
            isExisted(agentDTO);
            agentRepository.delete(ag);
            commitTransaction(session);
        } catch (Exception e) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            rollbackTransaction(session);
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAgent(AgentDTO agentDTO) throws Exception {
        try {
            initialize();
            Agent agent = convert(agentDTO);
            beginTransaction(session);
            isExisted(agentDTO);
            agentRepository.update(agent);
            commitTransaction(session);
        } catch (Exception e) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            rollbackTransaction(session);
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<AgentDTO> findAllAgents() throws Exception {
        List<AgentDTO> list;
        try {
            initialize();
            beginTransaction(session);
            List<Agent> agents = agentRepository.findAll();
            if (agents == null) {
                throw new Exception("List not found");
            }
            list = agents.stream().map(this::convert).collect(Collectors.toList());
            commitTransaction(session);
        } catch (Exception e) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            rollbackTransaction(session);
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public AgentDTO getAgentByEmail(String email) throws Exception {
        Agent agent;
        try {
            initialize();
            beginTransaction(session);
            agent = agentRepository.getByEmail(email);
            if (agent == null) {
                throw new Exception("Agent not found");
            }
            commitTransaction(session);
        } catch (Exception e) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            rollbackTransaction(session);
            throw e;
        } finally {
            session.close();
        }
        return convert(agent);
    }

    private void initialize() throws Exception {
        if (getSession() == null) {
            throw new Exception("session is null");
        }
        session = getSession();
        agentRepository = new AgentRepositoryImpl(getSession());
    }

    private Agent convert(@NotNull AgentDTO agentDTO) {
        return Agent.builder()
                .name(agentDTO.getName())
                .email(agentDTO.getEmail())
                .address(agentDTO.getAddress())
                .status(agentDTO.getStatus())
                .enrolledDate(agentDTO.getEnrolledDate())
                .accountBalance(agentDTO.getAccountBalance())
                .build();
    }

    private AgentDTO convert(@NotNull Agent agentDTO) {
        return AgentDTO.builder()
                .name(agentDTO.getName())
                .email(agentDTO.getEmail())
                .address(agentDTO.getAddress())
                .status(agentDTO.getStatus())
                .enrolledDate(agentDTO.getEnrolledDate())
                .accountBalance(agentDTO.getAccountBalance())
                .build();
    }

    private void isExisted(@NotNull AgentDTO agentDTO) throws Exception {
        if (agentRepository.getByEmail(agentDTO.getEmail()) == null) {
            throw new Exception("This agent does not exist");
        }
    }
}
