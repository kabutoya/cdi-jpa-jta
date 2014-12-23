package com.github.namioka.cdi_jpa_jta.experimental.infrastructure.jpa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;

@ApplicationScoped
@Alternative
public class ApplicationManagedEntityManagerProducer {

    @Inject
    private Logger logger;
    @Inject
    private EntityManagerFactory emf;

    @Dependent
    @Produces
    public EntityManager produce() {
        EntityManager em = EntityManagerProxy.newProxyInstance(emf.createEntityManager());
        if (logger.isDebugEnabled()) {
// TODO
//            if (Proxy.isProxyClass(em.getClass())) {
//                logger.debug("{} -> {}", EntityManager.class.getName(), em.getClass().getName());
//            } else {
//                logger.debug("{} -> {}", EntityManager.class.getName(), em.getClass().getName());
//            }
            logger.debug("{} -> {}", EntityManager.class.getName(), em.getClass().getName());
        }
        return em;
    }

    public void dispose(@Disposes EntityManager em) {
    }

    private static class EntityManagerProxy implements InvocationHandler {

        private final EntityManager em;

        public EntityManagerProxy(EntityManager em) {
            this.em = em;
        }

        public static EntityManager newProxyInstance(EntityManager em) {
            return (EntityManager) Proxy.newProxyInstance(
                    em.getClass().getClassLoader(), new Class<?>[]{EntityManager.class}, new EntityManagerProxy(em));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            em.joinTransaction();
            return method.invoke(em, args);
        }
    }
}
