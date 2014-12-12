package com.github.namioka.cdi_jpa_jta.experimental.domain.concept;

import java.io.Serializable;

public interface ReferenceObject<T extends ReferenceObject<T, ID>, ID extends Serializable> extends Serializable { // Also known as "Entity".

    ID getId();

    default boolean isNew() {
        return getId() == null;
    }

    default boolean sameIdentityAs(final T other) {
        if (this.isNew()) {
            return false;
        } else {
            //if (other != null && other instanceof ReferenceObject && !((ReferenceObject) other).isNew()) {
            //    return this.getId().equals(((ReferenceObject) other).getId());
            //} else {
            //    return false;
            //}
            return (other != null && !other.isNew()) ? this.getId().equals(other.getId()) : false;
        }
    }
}
