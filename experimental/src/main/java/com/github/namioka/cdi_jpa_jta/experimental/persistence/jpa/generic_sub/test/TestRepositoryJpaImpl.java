package com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa.generic_sub.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.Test;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.TestRepository;
import com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa.AbstractRepositoryJpaImpl;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestRepositoryJpaImpl extends AbstractRepositoryJpaImpl<Test, Long> implements TestRepository {
}
