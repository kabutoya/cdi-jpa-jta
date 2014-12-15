package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);

    boolean isGeneralizationOf(Specification<T> specification);

    default boolean isSpecialCaseOf(final Specification<T> specification) {
        if (specification == null) {
            throw new IllegalArgumentException("specification must not be null");
        }
        return specification.isGeneralizationOf(this);
    }

    default Specification<T> and(final Specification<T> specification) {
        return new ConjunctionSpecification<T>().with(this).with(specification);
    }

    default Specification<T> or(final Specification<T> specification) {
        return new DisjunctionSpecification<T>().with(this).with(specification);
    }
}
