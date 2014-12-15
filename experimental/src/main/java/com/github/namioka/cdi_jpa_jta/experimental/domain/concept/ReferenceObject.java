package com.github.namioka.cdi_jpa_jta.experimental.domain.concept;

import java.io.Serializable;

public interface ReferenceObject<T extends ReferenceObject<T, ID>, ID extends Serializable> extends Serializable {

    ID getId();

    default boolean isNew() {
        return getId() == null;
    }

    default boolean sameIdentityAs(final T other) {
        return (this.isNew() || (other == null || other.isNew()))
                ? false
                : this.getId().equals(other.getId());
    }
}
