package com.github.namioka.cdi_jpa_jta.experimental.domain.concept;

import java.io.Serializable;

public interface ValueObject<T extends ValueObject<T>> extends Serializable {

    default boolean isSameValueAs(final T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
