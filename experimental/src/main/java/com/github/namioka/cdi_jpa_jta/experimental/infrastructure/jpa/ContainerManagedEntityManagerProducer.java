package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;

@ApplicationScoped
@Alternative
public class ContainerManagedEntityManagerProducer {

    private static final String PERSISTENCE_UNIT_NAME = "test_PU"; // TODO inject
    @Inject
    private Logger logger;
    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @RequestScoped
    @Produces
    public EntityManager create() {
        return em;
    }

    public void dispose(@Disposes EntityManager em) {
    }
}
