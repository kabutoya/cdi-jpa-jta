package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import java.util.Arrays;
import java.util.List;

public interface CompositeSpecification<T> extends Specification<T> {

    List<Specification<T>> getComponents();

//    CompositeSpecification<T> with(Specification<T> specification);
//
//    CompositeSpecification<T> with(Specification<T>... specification);
//
    default CompositeSpecification<T> with(Specification<T>... specifications) {
        final boolean DEBUG = true;
        if (DEBUG) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(String.format("%s#with before", getClass().getSimpleName()));
            getComponents().stream().forEach(s -> {
                System.out.println(String.format("%s", s.getClass().getName()));
            });
        }
        getComponents().addAll(Arrays.asList(specifications));
        if (DEBUG) {
            System.out.println(String.format("%s#with after", getClass().getSimpleName()));
            getComponents().stream().forEach(s -> {
                System.out.println(String.format("%s", s.getClass().getName()));
            });
        }
        return this;
    }

    CompositeSpecification<T> remainderUnsatisfiedBy(T candidateObject);

//    @Slf4j @ToString
//    public static abstract class AbstractCompositeSpecification<T> implements CompositeSpecification<T> {
//
//        protected final List<Specification<T>> components;
//
//        public AbstractCompositeSpecification() {
//            this.components = new ArrayList<>();
//        }
//
//        public AbstractCompositeSpecification(Specification<T>... specifications) {
//            this();
//            this.components.addAll(Arrays.asList(specifications));
//        }
//
//        public AbstractCompositeSpecification(Specification<T> self, Specification<T>... specifications) {
//            // TODO self == null ??
//            this();
//            // TODO with ??
//            this.components.add(self);
//            if (specifications != null && specifications.length > 0) {
//                if (specifications.length == 1) {
//                    this.components.add(specifications[0]);
//                } else {
//                    this.components.addAll(Arrays.asList(specifications));
//                }
//            }
//        }
//
//        @Override
//        public CompositeSpecification<T> with(Specification<T> specification) {
//            if (log.isDebugEnabled()) {
//                log.debug("--------------------------------------------------------------------------------");
//                log.debug("{}#with before", getClass().getSimpleName());
//                components.stream().forEach(s -> {
//                    if (log.isDebugEnabled()) {
//                        log.debug("{}", s.getClass().getName());
//                    }
//                });
//            }
//            components.add(specification);
//            if (log.isDebugEnabled()) {
//                log.debug("{}#with after", getClass().getSimpleName());
//                components.stream().forEach(s -> {
//                    if (log.isDebugEnabled()) {
//                        log.debug("{}", s.getClass().getName());
//                    }
//                });
//            }
//            return this;
//        }
//    }
}
