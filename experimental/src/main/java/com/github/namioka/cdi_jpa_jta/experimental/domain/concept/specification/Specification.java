package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidateObject);

    boolean isSpecialCaseOf(Specification<T> specification);

    boolean isGeneralizationOf(Specification<T> specification);

    default Specification<T> and(final Specification<T>... specifications) {
        return new ConjunctionSpecification<T>().with(this).with(specifications);
    }

    default Specification<T> or(final Specification<T>... specifications) {
        return new DisjunctionSpecification<T>().with(this).with(specifications);
    }
}
