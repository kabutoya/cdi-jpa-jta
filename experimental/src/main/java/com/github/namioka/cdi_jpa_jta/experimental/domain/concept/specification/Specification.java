package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

public interface Specification<T> { // http://martinfowler.com/apsupp/spec.pdf

    boolean isSatisfiedBy(T candidateObject);

    default Specification<T> and(final Specification<T>... specifications) {
        return new ConjunctionSpecification<T>().with(this).with(specifications);
    }

    default Specification<T> or(final Specification<T>... specifications) {
        return new DisjunctionSpecification<T>().with(this).with(specifications);
    }
}
