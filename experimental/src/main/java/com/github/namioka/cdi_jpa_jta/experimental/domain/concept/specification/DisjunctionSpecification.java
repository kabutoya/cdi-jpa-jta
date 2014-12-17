package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.List;

public class DisjunctionSpecification<T> implements CompositeSpecification<T> {

    private final List<Specification<T>> components = new ArrayList<>();

    @Override
    public List<Specification<T>> getComponents() {
        return components;
    }

    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidate) {
        // TODO
        return getComponents().stream().filter(s -> s.isSatisfiedBy(candidate)).count() > 0
                ? new DisjunctionSpecification<>()
                : this;
    }

    @Override
    public boolean isGeneralizationOf(final Specification<T> specification) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSpecialCaseOf(final Specification<T> specification) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
