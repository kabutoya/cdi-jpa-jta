package com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa.generic_sub.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.Test;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.TestRepository;
import com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa.AbstractCrudRepositoryJpaImpl;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestRepositoryJpaImpl extends AbstractCrudRepositoryJpaImpl<Test, Long> implements TestRepository {

    @Override
    public List<Test> findAll() {
        return em.createQuery(String.format("select x from %s x", entityClass.getName()), entityClass).getResultList();
    }
}
