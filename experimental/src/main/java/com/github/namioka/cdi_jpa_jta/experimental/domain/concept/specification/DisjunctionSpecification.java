package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j @ToString(callSuper = true)
public class DisjunctionSpecification<T> implements CompositeSpecification<T> {

    @Getter
    private final List<Specification<T>> components = new ArrayList<>();

//    public DisjunctionSpecification() {
//        if (log.isDebugEnabled()) {
//            log.debug("constructed -> {}", getClass().getName());
//        }
//    }
//
    @Override
    public boolean isSatisfiedBy(final T candidateObject) {
        // http://martinfowler.com/apsupp/spec.pdf p13
        //
        // DisjunctionSpecification >> isSatisfiedBy: aCandidateObject
        //     ^ self components contains: [:each | each isSatisfiedBy: aCandidateObject ]
        return components.stream().anyMatch(s -> s.isSatisfiedBy(candidateObject));
    }

    @Override
    public boolean isSpecialCaseOf(final Specification<T> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isGeneralizationOf(final Specification<T> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidateObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
