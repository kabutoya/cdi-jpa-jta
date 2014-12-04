package com.github.namioka.cdi_jpa_jta.experimental.application;

import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.Test;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.TestRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;

@ApplicationScoped
public class TestApplicationService {

    @Inject
    private Logger logger;

    @Inject
    private TestRepository testRepository;

    @Transactional
    public void execute1() {
        testRepository.save(new Test("Love ", "Beer ?"));
    }

    @Transactional
    public void execute2() {
//        testRepository.findAll().stream().forEach(o -> {
//            logger.info(o.toString());
//        });
//
        logger.info(testRepository.findOne(1L).toString());
    }
}
