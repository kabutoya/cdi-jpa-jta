package com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa.generic_sub.model.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.model.test.Test;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.model.test.TestRepository;
import com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa.AbstractRepositoryJpaImpl;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestRepositoryJpaImpl extends AbstractRepositoryJpaImpl<Test, Long> implements TestRepository {
}
