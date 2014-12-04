package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;

@ApplicationScoped
public class ValidatorProducer {

    @Inject
    private Logger logger;

    @Inject
    private ValidatorFactory validatorFactory;

    @ApplicationScoped
    @Produces
    public Validator produce() {
        Validator validator = validatorFactory.getValidator();
        if (logger.isDebugEnabled()) {
            logger.debug("{} -> {}", Validator.class.getName(), validator.getClass().getName());
        }
        return validator;
    }

    public void dispose(@Disposes Validator validator) {
    }
}
