package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

// http://martinfowler.com/apsupp/spec.pdf
public interface Specification<T> {

    boolean isSatisfiedBy(T candidateObject);

//    default Specification<T> and(Specification<T> specification) {
//        return new ConjunctionSpecification().with(this).with(specification);
//    }
//
//    default Specification<T> or(Specification<T> specification) {
//        return new DisjunctionSpecification().with(this).with(specification);
//    }
//
    default Specification<T> and(Specification<T>... specifications) {
        //return new ConjunctionSpecification<>(this, specifications);
        return new ConjunctionSpecification<T>().with(this).with(specifications);
    }

    default Specification<T> or(Specification<T>... specifications) {
        //return new DisjunctionSpecification<>(this, specifications);
        return new DisjunctionSpecification<T>().with(this).with(specifications);
    }
}
