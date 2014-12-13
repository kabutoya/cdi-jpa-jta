package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.junit.Test;

public class CompositeSpecificationTest {

    private static final DummySpecification[] NULL = null;
    private static final DummySpecification[] EMPTY = new DummySpecification[0];
    private static final DummySpecification[] ONE = new DummySpecification[]{new DummySpecification()};
    private static final DummySpecification[] TWO = new DummySpecification[]{new DummySpecification(), new DummySpecification()};

    @Test(expected = IllegalArgumentException.class)
    public void with_should_throw_an_exception_when_specifications_is_null() {
        TestableCompositeSpecification testableCompositeSpecification = new TestableCompositeSpecification();
        testableCompositeSpecification.with(NULL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void with_should_throw_an_exception_when_specifications_is_empty() {
        TestableCompositeSpecification testableCompositeSpecification = new TestableCompositeSpecification();
        testableCompositeSpecification.with(EMPTY);
    }

    @Test
    public void with_should_return_1_when_argument_size_is_1() {
        TestableCompositeSpecification testableCompositeSpecification = new TestableCompositeSpecification();
        testableCompositeSpecification.with(ONE);
        assertThat("should_return_1_when_argument_size_is_1", testableCompositeSpecification.getComponents().size(), is(1));
    }

    @Test
    public void with_should_return_2_when_argument_size_is_2() {
        TestableCompositeSpecification testableCompositeSpecification = new TestableCompositeSpecification();
        testableCompositeSpecification.with(TWO);
        assertThat("should_return_2_when_argument_size_is_2", testableCompositeSpecification.getComponents().size(), is(2));
    }

    private static class TestableCompositeSpecification<String> implements CompositeSpecification<String> {

        @Getter
        private final List<Specification<String>> components = new ArrayList<>();

        @Override
        public CompositeSpecification<String> remainderUnsatisfiedBy(String candidateObject) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isSatisfiedBy(String candidateObject) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isSpecialCaseOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class DummySpecification<Integer> implements Specification<Integer> {

        @Override
        public boolean isSatisfiedBy(Integer candidateObject) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isSpecialCaseOf(Specification<Integer> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isGeneralizationOf(Specification<Integer> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
