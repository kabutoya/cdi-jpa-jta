package com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.conception.CrudRepository;
import java.util.List;

public interface TestRepository extends CrudRepository<Test, Long> {

    List<Test> findAll();
}
