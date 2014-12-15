package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
public class ConjunctionSpecification<T> implements CompositeSpecification<T> {

    @Getter
    private final List<Specification<T>> components = new ArrayList<>();

//    @Override
//    public boolean isSatisfiedBy(final T candidate) {
//        return getComponents().stream().allMatch(s -> s.isSatisfiedBy(candidate));
//    }
//
    @Override
    public CompositeSpecification<T> remainderUnsatisfiedBy(final T candidate) {
        CompositeSpecification<T> unsatisfied = new ConjunctionSpecification<>();
        getComponents().stream().filter(s -> !s.isSatisfiedBy(candidate)).forEach(unsatisfied::with);
        return unsatisfied;
    }
}
