package com.github.namioka.cdi_jpa_jta.experimental.application;

import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.model.test.Test;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.model.test.TestRepository;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.model.test.TestValue;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.slf4j.Logger;

@ApplicationScoped
public class TestApplicationService {

    @Inject
    private Logger logger;

    @Inject
    private Validator validator;

    @Inject
    private TestRepository testRepository;

    @Transactional
    public void execute1(final Long id, final String value1, final String value2) {
        Test test;

        if (id == null) {
            test = new Test(new TestValue(value1, value2));
        } else {
            test = testRepository.find(id);
            test.setTestValue(new TestValue(value1, value2));
        }

        if (test == null) {
            throw new IllegalArgumentException("test is null");
        }

        Set<ConstraintViolation<Test>> constraintViolations = validator.validate(test);
        if (!constraintViolations.isEmpty()) {
            constraintViolations.stream().forEach(v -> {
                logger.error("messageTemplate=[{}],  message=[{}], propertyPath=[{}]", v.getMessageTemplate(), v.getMessage(), v.getPropertyPath());
            });
            throw new ConstraintViolationException(constraintViolations);
        }

        testRepository.store(test);
    }

    @Transactional
    public void execute2() {
        Test test = testRepository.find(1L);
        logger.info(test.toString());

        testRepository.findAll().stream().forEach(o -> {
            logger.info(o.toString());
        });
    }
}
