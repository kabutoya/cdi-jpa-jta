package com.github.namioka.cdi_jpa_jta.experimental.domain.concept;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends AggregateRoot<T, ID>, ID extends Serializable> {

    <R extends T> R store(R entity);

    T find(ID id);

    List<T> findAll();
}
