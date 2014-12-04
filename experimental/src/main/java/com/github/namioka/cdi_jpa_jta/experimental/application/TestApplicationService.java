package com.github.namioka.cdi_jpa_jta.experimental.application;

import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.Test;
import com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test.TestRepository;
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
    public void execute1() {
        // Actually, entity(=Test) is created by user input...
        Test test = createTest();

        // Validate entity before saving...
        Set<ConstraintViolation<Test>> constraintViolations = validator.validate(test);
        if (!constraintViolations.isEmpty()) {
            constraintViolations.stream().forEach(v -> {
                logger.error("messageTemplate=[{}],  message=[{}], propertyPath=[{}]", v.getMessageTemplate(), v.getMessage(), v.getPropertyPath());
            });
            throw new ConstraintViolationException(constraintViolations);
        }

        testRepository.save(test);
    }

    private Test createTest() {
        Test test = new Test("You.", "Love Beer?");
        //Test test = new Test("Oops!", null);
        return test;
    }

    @Transactional
    public void execute2() {
        Test test = testRepository.findOne(1L);
        logger.info(test.toString());

        testRepository.findAll().stream().forEach(o -> {
            logger.info(o.toString());
        });
    }
}
