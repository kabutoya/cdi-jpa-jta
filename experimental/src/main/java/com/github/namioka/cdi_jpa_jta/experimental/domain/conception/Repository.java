package com.github.namioka.cdi_jpa_jta.experimental.domain.conception;

import java.io.Serializable;

public interface Repository<T extends Persistable<ID>, ID extends Serializable> {
}
