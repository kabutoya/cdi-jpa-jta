package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.List;

public class ConjunctionSpecification<T> implements CompositeSpecification<T> {

    private final List<Specification<T>> components = new ArrayList<>();

    @Override
    public List<Specification<T>> getComponents() {
        return components;
    }

    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidate) {
        return getComponents().stream().filter(s -> !s.isSatisfiedBy(candidate))
                .collect(() -> new ConjunctionSpecification<>(),
                        (x, y) -> x.with(y),
                        (x, y) -> y.getComponents().stream().forEach(x::with) // TODO
                );
    }

    @Override
    public boolean isGeneralizationOf(final Specification<T> specification) {
        return getComponents().stream().allMatch(s -> s.isGeneralizationOf(specification));
    }

    @Override
    public boolean isSpecialCaseOf(final Specification<T> specification) {
        return getComponents().stream().allMatch(s -> s.isSpecialCaseOf(specification));
    }
}
