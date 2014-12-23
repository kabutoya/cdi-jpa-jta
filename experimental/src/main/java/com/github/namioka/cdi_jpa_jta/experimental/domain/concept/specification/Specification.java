package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);

    boolean isGeneralizationOf(Specification<T> specification);

    default boolean isSpecialCaseOf(final Specification<T> specification) {
        if (this == specification) {
            return true;
        }
        if (specification == null) {
            return false;
        }
        return specification.isGeneralizationOf(this);
    }

    default Specification<T> and(final Specification<T> specification) {
        if (this instanceof CompositeSpecification) {
            if (this instanceof ConjunctionSpecification) {
                return ((ConjunctionSpecification<T>) this).with(specification);
            }
            if (this instanceof DisjunctionSpecification) {
                final DisjunctionSpecification<T> real = (DisjunctionSpecification<T>) this;
                final Specification<T> tail = real.getComponents().remove(real.getComponents().size() - 1);
                return real.with(tail.and(specification));
            }
            throw new UnsupportedOperationException(this.getClass().getName());
        } else {
            return new ConjunctionSpecification<T>().with(this).with(specification);
        }
    }

    default Specification<T> or(final Specification<T> specification) {
        if (this instanceof CompositeSpecification) {
            if (this instanceof ConjunctionSpecification) {
                return new DisjunctionSpecification<T>().with(this).with(specification);
            }
            if (this instanceof DisjunctionSpecification) {
                return ((DisjunctionSpecification<T>) this).with(specification);
            }
            throw new UnsupportedOperationException(this.getClass().getName());
        } else {
            return new DisjunctionSpecification<T>().with(this).with(specification);
        }
    }
}
