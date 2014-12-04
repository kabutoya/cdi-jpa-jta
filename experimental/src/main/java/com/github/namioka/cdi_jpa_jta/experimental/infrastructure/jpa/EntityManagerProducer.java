package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.jpa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    private EntityManagerFactory emf;

    @Dependent
    @Produces
    public EntityManager produce() {
        return EntityManagerProxy.newInstance(emf.createEntityManager());
    }

    private static class EntityManagerProxy implements InvocationHandler {

        private final EntityManager em;

        public static EntityManager newInstance(EntityManager em) {
            return (EntityManager) Proxy.newProxyInstance(
                    em.getClass().getClassLoader(), new Class<?>[]{EntityManager.class}, new EntityManagerProxy(em));
        }

        public EntityManagerProxy(EntityManager em) {
            this.em = em;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            em.joinTransaction();
            return method.invoke(em, args);
        }
    }
}
