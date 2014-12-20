package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisjunctionSpecification<T> implements CompositeSpecification<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisjunctionSpecification.class);
    private final List<Specification<T>> components = new ArrayList<>();

    @Override
    public List<Specification<T>> getComponents() {
        return components;
    }

    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidate) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("#remainderUnsatisfiedBy candidate --[{}]--", candidate);
        }
        return getComponents().stream()
                .peek(s -> {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("#remainderUnsatisfiedBy {}", s);
                    }
                })
                .filter(s -> s.isSatisfiedBy(candidate))
                .peek(s -> {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("#remainderUnsatisfiedBy -> satisfied {}", s);
                    }
                })
                .count() > 0
                        ? new DisjunctionSpecification<>()
                        : this;
    }

    @Override
    public boolean isGeneralizationOf(final Specification<T> specification) {
        return getComponents().stream().anyMatch(s -> s.isGeneralizationOf(specification));
    }
//
//    @Override
//    public boolean isSpecialCaseOf(final Specification<T> specification) {
//        return getComponents().stream().anyMatch(s -> s.isSpecialCaseOf(specification));
//    }
}
