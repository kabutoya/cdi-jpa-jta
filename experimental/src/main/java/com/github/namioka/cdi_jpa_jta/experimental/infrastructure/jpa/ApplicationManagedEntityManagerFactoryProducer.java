package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;

@ApplicationScoped
@Alternative
public class ApplicationManagedEntityManagerFactoryProducer {

    private static final String PERSISTENCE_UNIT_NAME = "test_PU"; // TODO inject

    @Inject
    private Logger logger;

    @ApplicationScoped
    @Produces
    public EntityManagerFactory produce() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (logger.isDebugEnabled()) {
            logger.debug("{} -> {}", EntityManagerFactory.class.getName(), emf.getClass().getName());
        }
        return emf;
    }

    public void dispose(@Disposes EntityManagerFactory emf) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
