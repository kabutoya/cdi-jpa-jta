package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConjunctionSpecification<T> implements CompositeSpecification<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConjunctionSpecification.class);
    private final List<Specification<T>> components = new ArrayList<>();

    @Override
    public List<Specification<T>> getComponents() {
        return components;
    }

    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidate) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("remainderUnsatisfiedBy candidate --[{}]--", candidate);
        }
        return getComponents().stream()
                .peek(s -> {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("remainderUnsatisfiedBy {}", s);
                    }
                })
                .filter(s -> !s.isSatisfiedBy(candidate))
                .peek(s -> {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("remainderUnsatisfiedBy -> unsatisfied {}", s);
                    }
                })
                .collect(ConjunctionSpecification::new, //() -> new ConjunctionSpecification<>(),
                        (x, y) -> x.with(y),
                        (x, y) -> y.getComponents().stream().forEach(x::with));
    }

    @Override
    public boolean isGeneralizationOf(final Specification<T> specification) {
        return getComponents().stream().allMatch(s -> s.isGeneralizationOf(specification));
    }

    @Override
    public String toString() {
        return getComponents().stream().map(s -> s.toString()).collect(joining(" && ", "(", ")"));
    }
}
