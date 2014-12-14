package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j @ToString(callSuper = true)
public class ConjunctionSpecification<T> implements CompositeSpecification<T> {

    @Getter
    private final List<Specification<T>> components = new ArrayList<>();

    public ConjunctionSpecification() {
        if (log.isDebugEnabled()) {
            log.debug("constructed -> {}", getClass().getName());
        }
    }

    @Override
    public boolean isSatisfiedBy(final T candidateObject) {
        // http://martinfowler.com/apsupp/spec.pdf p10
        //
        // CompositeSpecification isSatisfiedBy: aCandidate
        //         (self components do: [:each |
        //         (each isSatisfiedBy: aCandidate) ifFalse: [^false]
        //         ]
        //         ^true
        return components.stream().allMatch(s -> s.isSatisfiedBy(candidateObject));
    }

    @Override
    public boolean isSpecialCaseOf(Specification<T> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isGeneralizationOf(Specification<T> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidateObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
