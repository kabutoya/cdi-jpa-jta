package com.github.namioka.cdi_jpa_jta.experimental.application;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestApplicationServiceIT {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.github.namioka.cdi_jpa_jta")
                .addAsResource("META-INF/beans.xml", "META-INF/beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
        JavaArchive[] dependencies = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .as(JavaArchive.class);
        war.addAsLibraries(dependencies);
        System.out.println(war.toString(true));
        return war;
    }

    @Inject
    private TestApplicationService testApplicationService;

    @Test
    public void test() {
        testApplicationService.execute1(null, "You,", "Love Beer?");
        testApplicationService.execute1(1L, "You,", "Love Sake?");
        testApplicationService.execute1(1L, "You,", "Love Wine?");
        testApplicationService.execute2();
    }
}
