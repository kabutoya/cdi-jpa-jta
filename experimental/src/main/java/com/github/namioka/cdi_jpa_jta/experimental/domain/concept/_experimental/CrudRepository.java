//package com.github.namioka.cdi_jpa_jta.experimental.domain.concept._experimental;
//
//import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.Repository;
//import java.io.Serializable;
//import java.util.List;
//
//public interface CrudRepository<T extends Persistable<ID>, ID extends Serializable> extends Repository<T, ID> {
//
//    <S extends T> S save(S entity);
//
//    <S extends T> List<S> save(List<S> entities);
//
//    T findOne(ID id);
//
//    boolean exists(ID id);
//
//// TODO The find"All" is too dangerous!
////    List<T> findAll();
////
//    List<T> findAll(List<ID> ids);
//
//    long count();
//
//    void delete(ID id);
//
//    void delete(T entity);
//
//    void delete(List<? extends T> entities);
//
//    void deleteAll();
//
////    <S extends T> S save(S entity);
////
////    <S extends T> Iterable<S> save(Iterable<S> entities);
////
////    T findOne(ID id);
////
////    boolean exists(ID id);
////
////    Iterable<T> findAll();
////
////    Iterable<T> findAll(Iterable<ID> ids);
////
////    long count();
////
////    void delete(ID id);
////
////    void delete(T entity);
////
////    void delete(Iterable<? extends T> entities);
////
////    void deleteAll();
//}
