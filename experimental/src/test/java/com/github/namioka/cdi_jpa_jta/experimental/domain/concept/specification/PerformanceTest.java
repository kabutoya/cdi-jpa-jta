package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.Getter;
import org.junit.Test;

public class PerformanceTest {

    private static final int MAX = 100;
    private Map<String, List<Long>> average;

    @Test
    public void test() {
        average = new HashMap<>();
        for (int j = 0; j < 50000; j++) {
            testInternal();
        }
//        average.entrySet().stream()
//                // TODO reduce
//                .forEach(e -> System.out.println(
//                                String.format("%s -> %.10f",
//                                        e.getKey(),
//                                        e.getValue().stream().mapToLong(t -> t).average().getAsDouble()
//                                )
//                        )
//                );
        average.entrySet().stream()
                .map(e -> {
                    return String.format("%s -> %.10f",
                            e.getKey(),
                            e.getValue().stream().mapToLong(t -> t).average().getAsDouble()
                    );
                })
                .sorted()
                .forEach(System.out::println);
    }

    private void testInternal() {
        TestableCompositeSpecificationA1 testableCompositeSpecificationA1 = new TestableCompositeSpecificationA1();
        TestableCompositeSpecificationB1 testableCompositeSpecificationB1 = new TestableCompositeSpecificationB1();
        TestableCompositeSpecificationC1 testableCompositeSpecificationC1 = new TestableCompositeSpecificationC1();
        TestableCompositeSpecificationD1 testableCompositeSpecificationD1 = new TestableCompositeSpecificationD1();
        TestableCompositeSpecificationE1 testableCompositeSpecificationE1 = new TestableCompositeSpecificationE1();

        fire(testableCompositeSpecificationA1, new DummySpecification());
        fire(testableCompositeSpecificationB1, new DummySpecification());
        fire(testableCompositeSpecificationC1, new DummySpecification());
        fire(testableCompositeSpecificationD1, new DummySpecification());
        fire(testableCompositeSpecificationE1, new DummySpecification());

        fire(testableCompositeSpecificationA1, new DummySpecification(), new DummySpecification(), new DummySpecification());
        fire(testableCompositeSpecificationB1, new DummySpecification(), new DummySpecification(), new DummySpecification());
        fire(testableCompositeSpecificationC1, new DummySpecification(), new DummySpecification(), new DummySpecification());
        fire(testableCompositeSpecificationD1, new DummySpecification(), new DummySpecification(), new DummySpecification());
        fire(testableCompositeSpecificationE1, new DummySpecification(), new DummySpecification(), new DummySpecification());
    }

    private <T> void fire(final CompositeSpecification<T> compositeSpecification, final Specification<T>... specifications) {
        if (average == null) {
            throw new IllegalStateException("average must not be null");
        }

        final String key = String.format("%s(%s)", specifications.length == 1 ? "single" : "multiple", compositeSpecification.getClass().getName());
        if (!average.containsKey(key)) {
            average.put(key, new ArrayList<>());
        }

        final long s = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            compositeSpecification.with(specifications);
        }
        final long e = System.currentTimeMillis();

        average.get(key).add((e - s));
    }

    private static abstract class AbstractTestableCompositeSpecification<String> implements CompositeSpecification<String> {

        @Getter
        protected final List<Specification<String>> components = new ArrayList<>(MAX);

        @Override
        public CompositeSpecification<String> remainderUnsatisfiedBy(String candidateObject) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isSatisfiedBy(String candidateObject) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isSpecialCaseOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private static class TestableCompositeSpecificationA1<String> extends AbstractTestableCompositeSpecification<String> {

        @Override
        public CompositeSpecification<String> with(Specification<String>... specifications) {
            if (specifications.length == 1) {
                getComponents().add(specifications[0]);
            } else {
                getComponents().addAll(Arrays.asList(specifications));
            }
            return this;
        }
    }

    private static class TestableCompositeSpecificationB1<String> extends AbstractTestableCompositeSpecification<String> {

        @Override
        public CompositeSpecification<String> with(Specification<String>... specifications) {
            getComponents().addAll(Arrays.asList(specifications));
            return this;
        }
    }

    private static class TestableCompositeSpecificationC1<String> extends AbstractTestableCompositeSpecification<String> {

        @Override
        public CompositeSpecification<String> with(Specification<String>... specifications) {
            final List<Specification<String>> tempComponents = getComponents();
            Stream.of(specifications).forEach(tempComponents::add);
            return this;
        }
    }

    private static class TestableCompositeSpecificationD1<String> extends AbstractTestableCompositeSpecification<String> {

        @Override
        public CompositeSpecification<String> with(Specification<String>... specifications) {
            Stream.of(specifications).forEach(getComponents()::add);
            return this;
        }
    }

    private static class TestableCompositeSpecificationE1<String> extends AbstractTestableCompositeSpecification<String> {

        @Override
        public CompositeSpecification<String> with(Specification<String>... specifications) {
            Arrays.stream(specifications).forEach(getComponents()::add);
            return this;
        }
    }

    private static class DummySpecification<String> implements Specification<String> {

        @Override
        public boolean isSatisfiedBy(String candidateObject) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isSpecialCaseOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
