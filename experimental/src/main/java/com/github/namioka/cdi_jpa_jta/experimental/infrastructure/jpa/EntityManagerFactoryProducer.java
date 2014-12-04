package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {

    private static final String PERSISTENCE_UNIT_NAME = "test_PU"; // TODO inject

    @ApplicationScoped
    @Produces
    public EntityManagerFactory produce() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void dispose(@Disposes EntityManagerFactory emf) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
