package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.CompositeSpecification;
import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.Specification;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class TestableCompositeSpecification implements CompositeSpecification<String> {

    @Getter
    private final List<Specification<String>> components = new ArrayList<>();

    @Override
    public CompositeSpecification<String> remainderUnsatisfiedBy(String candidate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isGeneralizationOf(Specification<String> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
