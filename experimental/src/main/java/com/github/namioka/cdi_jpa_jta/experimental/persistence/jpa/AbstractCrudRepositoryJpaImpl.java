package com.github.namioka.cdi_jpa_jta.experimental.persistence.jpa;

import com.github.namioka.cdi_jpa_jta.experimental.domain.conception.CrudRepository;
import com.github.namioka.cdi_jpa_jta.experimental.domain.conception.Persistable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractCrudRepositoryJpaImpl<T extends Persistable<ID>, ID extends Serializable> implements CrudRepository<T, ID> {

//    @Inject
//    private Logger logger;
//
    @Inject
    protected EntityManager em;

    protected final Class<T> entityClass;
    //private final Class<ID> idClass;

    public AbstractCrudRepositoryJpaImpl() {

        // TODO create resolver...
        ParameterizedType parameterizedType
                = getClass().getSimpleName().endsWith("$Proxy$_$$_WeldClientProxy")
                        ? (ParameterizedType) getClass().getSuperclass().getGenericSuperclass()
                        : (ParameterizedType) getClass().getGenericSuperclass();

        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        //this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
// TODO Not injected in constructor...orz
//        if (logger.isDebugEnabled()) {
//            logger.debug("entityClass={}, idClass={}", this.entityClass, this.idClass);
//        }
    }

    @Override
    public <S extends T> S save(S entity) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (entity.isNew()) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    public <S extends T> List<S> save(List<S> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T findOne(ID id) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return em.find(entityClass, id);
    }

    @Override
    public boolean exists(ID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    @Override
//    public List<T> findAll() {
//        //throw new UnsupportedOperationException("Not supported yet.");
//        return em.createQuery(String.format("select x from %s x", entityClass.getName()), entityClass).getResultList();
//    }
//
    @Override
    public List<T> findAll(List<ID> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(ID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(T entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(List<? extends T> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
