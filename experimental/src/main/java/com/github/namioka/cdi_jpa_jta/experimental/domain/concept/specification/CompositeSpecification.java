package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static java.util.Arrays.*;

import java.util.List;

public interface CompositeSpecification<T> extends Specification<T> {

    List<Specification<T>> getComponents();

    default CompositeSpecification<T> with(final Specification<T>... specifications) {
        if (specifications == null || specifications.length == 0) {
            throw new IllegalArgumentException("specifications must not be empty");
        }

        // TODO validation
        if (specifications.length == 1) {
            getComponents().add(specifications[0]);
        } else {
            getComponents().addAll(asList(specifications));
        }
        return this;
    }

    // TODO should return CompositeSpecification<T> ??
    Specification<T> remainderUnsatisfiedBy(T candidateObject);
}
