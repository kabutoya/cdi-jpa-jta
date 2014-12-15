package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.List;

public interface CompositeSpecification<T> extends Specification<T> {

    List<Specification<T>> getComponents();

    CompositeSpecification<T> remainderUnsatisfiedBy(T candidate);

    default CompositeSpecification<T> with(final Specification<T> specification) {
        if (specification == null) {
            throw new IllegalArgumentException("specification must not be null");
        }
        getComponents().add(specification);
        return this;
    }

    @Override
    default boolean isSatisfiedBy(T candidate) {
        return remainderUnsatisfiedBy(candidate).getComponents().isEmpty();
    }

    @Override
    default boolean isGeneralizationOf(final Specification<T> specification) {
        return getComponents().stream().allMatch(s -> s.isGeneralizationOf(specification));
    }

    @Override
    default boolean isSpecialCaseOf(final Specification<T> specification) {
        return getComponents().stream().allMatch(s -> s.isSpecialCaseOf(specification));
    }
}
