package com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.ReferenceObject;
import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.Repository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractRepositoryJpaImpl<T extends ReferenceObject<T, ID>, ID extends Serializable> implements Repository<T, ID> {

    @Inject
    protected EntityManager em;

    protected final Class<T> entityClass;
    //protected final Class<ID> idClass;

    @SuppressWarnings("unchecked")
    public AbstractRepositoryJpaImpl() {
        ParameterizedType parameterizedType
                = getClass().getSimpleName().endsWith("$Proxy$_$$_WeldClientProxy")
                        ? (ParameterizedType) getClass().getSuperclass().getGenericSuperclass()
                        : (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        //this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
    }

    @Override
    public <R extends T> R store(final R entity) {
        if (entity.isNew()) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    public List<T> findAll() {
        //return em.createQuery(String.format("select x from %s x", entityClass.getName()), entityClass).getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> r = cq.from(entityClass);
        return em.createQuery(cq.select(r)).getResultList();
    }

    @Override
    public T find(final ID id) {
        return em.find(entityClass, id);
    }

//    @Override
//    public <S extends T> List<S> save(List<S> entities) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public T findOne(ID id) {
//        //throw new UnsupportedOperationException("Not supported yet.");
//        return em.find(entityClass, id);
//    }
//
//    @Override
//    public boolean exists(ID id) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
////    @Override
////    public List<T> findAll() {
////        //throw new UnsupportedOperationException("Not supported yet.");
////        return em.createQuery(String.format("select x from %s x", entityClass.getName()), entityClass).getResultList();
////    }
////
//    @Override
//    public List<T> findAll(List<ID> ids) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public long count() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void delete(ID id) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void delete(T entity) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void delete(List<? extends T> entities) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void deleteAll() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
