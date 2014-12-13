package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

// http://martinfowler.com/apsupp/spec.pdf
public interface Specification<T> {

    boolean isSatisfiedBy(T candidateObject);

    default Specification<T> and(Specification<T>... specifications) {
        return new ConjunctionSpecification<T>().with(this).with(specifications);
    }

    default Specification<T> or(Specification<T>... specifications) {
        return new DisjunctionSpecification<T>().with(this).with(specifications);
    }
}
