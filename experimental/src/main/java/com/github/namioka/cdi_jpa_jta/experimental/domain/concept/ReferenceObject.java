package com.github.namioka.cdi_jpa_jta.experimental.domain.concept;

import java.io.Serializable;

public interface ReferenceObject<T, ID extends Serializable> extends Serializable {

    ID getId();

    default boolean isNew() {
        return getId() == null;
    }

    default boolean sameIdentityAs(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
