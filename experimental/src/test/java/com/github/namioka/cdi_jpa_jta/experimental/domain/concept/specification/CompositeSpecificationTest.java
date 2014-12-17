package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub.TestableCompositeSpecification;
import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub.TestableSpecification;
import org.junit.Test;

public class CompositeSpecificationTest {

    @Test(expected = IllegalArgumentException.class)
    public void with_should_throw_an_exception_when_argument_is_null() {
        TestableCompositeSpecification testableCompositeSpecification = new TestableCompositeSpecification();
        testableCompositeSpecification.with(null);
    }

    @Test
    public void with_should_not_throw_an_exception_when_argument_is_not_null() {
        TestableCompositeSpecification testableCompositeSpecification = new TestableCompositeSpecification();
        testableCompositeSpecification.with(new TestableSpecification(new String[]{"A"}));
    }
}
