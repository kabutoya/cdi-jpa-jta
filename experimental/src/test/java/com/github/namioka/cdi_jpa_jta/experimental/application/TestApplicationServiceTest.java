package com.github.namioka.cdi_jpa_jta.experimental.application;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.github.namioka.cdi_jpa_jta.NamingRule;
import com.github.namioka.cdi_jpa_jta.WeldRule;
import javax.enterprise.inject.spi.CDI;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

public class TestApplicationServiceTest {

    @ClassRule
    public static final TestRule RULE = RuleChain.outerRule(new NamingRule()).around(new WeldRule());
//    public static final NamingRule namingRule = new NamingRule();

//    private Weld weld;
//    private WeldContainer container;
//
//    @Before
//    public void before() {
//        weld = new Weld();
//        container = weld.initialize();
//    }
//
//    @After
//    public void after() {
//        weld.shutdown();
//    }
//
    @Test
    public void test1() {
        assertThat("sample test.", "actual", not(equalTo("expected")));
    }

    @Test
    public void test2() {
        TestApplicationService testApplicationService = CDI.current().select(TestApplicationService.class).get();
        testApplicationService.execute1();
        testApplicationService.execute2();
    }
}
