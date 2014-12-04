package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.logging;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dependent
public class LoggerProducer {

    @Dependent
    @Produces
    Logger createLogger(final InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }
}
