package com.github.namioka.cdi_jpa_jta.experimental.domain.concept;

import java.io.Serializable;

public interface AggregateRoot<T extends AggregateRoot<T, ID>, ID extends Serializable> extends ReferenceObject<T, ID> {
}
