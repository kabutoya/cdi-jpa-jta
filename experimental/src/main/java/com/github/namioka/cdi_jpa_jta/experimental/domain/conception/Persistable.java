package com.github.namioka.cdi_jpa_jta.experimental.domain.conception;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> extends Serializable {

    ID getId();

    default boolean isNew() {
        return getId() == null;
    }
}
