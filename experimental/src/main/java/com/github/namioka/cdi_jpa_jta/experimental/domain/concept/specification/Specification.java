package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);

    boolean isGeneralizationOf(Specification<T> specification);

    default boolean isSpecialCaseOf(final Specification<T> specification) {
        if (specification == null) {
            throw new IllegalArgumentException("specification must not be null");
        }
        return specification.isGeneralizationOf(this);
    }

    default Specification<T> and(final Specification<T> specification) {
        if (this instanceof CompositeSpecification) {
            if (this instanceof ConjunctionSpecification) {
                return ((ConjunctionSpecification<T>) this).with(specification);
            }
            if (this instanceof DisjunctionSpecification) {
                final Specification<T> component = ((DisjunctionSpecification<T>) this).poll();
                if (component instanceof CompositeSpecification) {
                    if (component instanceof ConjunctionSpecification) {
                        // A or component:(B and C) -> A or component:(B and C and specification))
                        return ((DisjunctionSpecification<T>) this)
                                .with(((ConjunctionSpecification<T>) component).with(specification));
                    }
                    if (component instanceof DisjunctionSpecification) {
                        // A or component:(B or C) -> A or component:(B or (C and specification))
                        final Specification<T> c = ((DisjunctionSpecification<T>) component).poll();
                        return ((DisjunctionSpecification<T>) this)
                                .with(component)
                                .with(new ConjunctionSpecification<T>().with(c).with(specification));
                    }
                    throw new IllegalStateException();
                } else {
                    // A or component -> A or (component and specification)
                    return ((DisjunctionSpecification<T>) this)
                            .with(new ConjunctionSpecification<T>().with(component).with(specification));
                }
            }
            throw new IllegalStateException();
        } else {
            return new ConjunctionSpecification<T>().with(this).with(specification);
        }
    }

    default Specification<T> or(final Specification<T> specification) {
        if (this instanceof CompositeSpecification) {
            if (this instanceof ConjunctionSpecification) {

                final Specification<T> component = ((DisjunctionSpecification<T>) this).poll();
                if (component instanceof CompositeSpecification) {

                    //-----
                    if (component instanceof ConjunctionSpecification) {
                        // A and component:(B and C) -> A or component:(B and C and specification))
                        return ((DisjunctionSpecification<T>) this)
                                .with(((ConjunctionSpecification<T>) component).with(specification));
                    }
                    if (component instanceof DisjunctionSpecification) {
                        // A and component:(B or C) -> A or component:(B or (C and specification))
                        final Specification<T> c = ((DisjunctionSpecification<T>) component).poll();
                        return ((DisjunctionSpecification<T>) this)
                                .with(component)
                                .with(new ConjunctionSpecification<T>().with(c).with(specification));
                    }
                    //-----

                    throw new IllegalStateException();
                } else {
                    // A and component -> A and (component or specification)
                    return ((ConjunctionSpecification<T>) this)
                            .with(new DisjunctionSpecification<T>().with(component).with(specification));
                }
            }
            if (this instanceof DisjunctionSpecification) {
                return ((DisjunctionSpecification<T>) this).with(specification);
            }
            throw new IllegalStateException();
        } else {
            return new DisjunctionSpecification<T>().with(this).with(specification);
        }
    }
}
