package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.Arrays;
import java.util.List;

public interface CompositeSpecification<T> extends Specification<T> {

    List<Specification<T>> getComponents();

    default CompositeSpecification<T> with(Specification<T>... specifications) {
        final boolean DEBUG = true;
        if (DEBUG) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(String.format("%s#with before", getClass().getSimpleName()));
            getComponents().stream().forEach(s -> {
                System.out.println(String.format("%s", s.getClass().getName()));
            });
        }
        getComponents().addAll(Arrays.asList(specifications));
        if (DEBUG) {
            System.out.println(String.format("%s#with after", getClass().getSimpleName()));
            getComponents().stream().forEach(s -> {
                System.out.println(String.format("%s", s.getClass().getName()));
            });
        }
        return this;
    }

    CompositeSpecification<T> remainderUnsatisfiedBy(T candidateObject);
}
