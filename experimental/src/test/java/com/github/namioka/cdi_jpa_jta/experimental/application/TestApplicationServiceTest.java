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

    @Test
    public void test1() {
        assertThat("sample test.", "actual", not(equalTo("expected")));
    }

    @Test
    public void test2() {
        TestApplicationService testApplicationService = CDI.current().select(TestApplicationService.class).get();
        testApplicationService.execute1(null, "You,", "Love Beer?");
        testApplicationService.execute1(1L, "You,", "Love Sake?");
        testApplicationService.execute1(1L, "You,", "Love Wine?");
        testApplicationService.execute2();
    }
}
