package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

public interface ValueBoundSpecification<T, V> extends LeafSpecification<T> {

    V getValue();
}
