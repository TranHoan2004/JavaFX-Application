package com.he180030.agentmanagement.service.impl;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BaseService {
    protected final static String CONFIG_FILE = "hibernate.cfg.xml";
    protected Configuration configuration;
    protected Transaction transaction;
    @Getter
    protected SessionFactory sessionFactory;

    public BaseService() {
        configuration = new Configuration();
        configuration.configure(CONFIG_FILE);
        sessionFactory = configuration.buildSessionFactory();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void beginTransaction(Session session) throws Exception {
        if (session != null) {
            transaction = session.beginTransaction();
        }
        throw new Exception("Session is null");
    }

    protected void commitTransaction(Session session) throws Exception {
        transaction.commit();
        if (session != null) {
            transaction.commit();
        }
        throw new Exception("Session is null");
    }

    protected void rollbackTransaction(Session session) {
        transaction.rollback();
    }
}
