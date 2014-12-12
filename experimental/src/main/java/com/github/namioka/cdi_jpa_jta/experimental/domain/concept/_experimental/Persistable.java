//package com.github.namioka.cdi_jpa_jta.experimental.domain.concept._experimental;
//
//import java.io.Serializable;
//
//public interface Persistable<ID extends Serializable> extends Serializable {
//
//    ID getId();
//
//    default boolean isNew() {
//        return getId() == null;
//    }
//}
