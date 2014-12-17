package com.github.namioka.cdi_jpa_jta;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.rules.ExternalResource;
// TODO https://developer.jboss.org/wiki/CreatingUnitTestsWithWeldAndJunit4

public class WeldRule extends ExternalResource {

    private Weld weld;
    private WeldContainer container;

    @Override
    protected void before() throws Throwable {
        weld = new Weld();
        container = weld.initialize();
    }

    @Override
    protected void after() {
        weld.shutdown();
    }
}
