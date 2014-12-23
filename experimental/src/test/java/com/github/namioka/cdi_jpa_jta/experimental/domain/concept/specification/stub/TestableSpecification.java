package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.CompositeSpecification;
import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.ConjunctionSpecification;
import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.DisjunctionSpecification;
import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.Specification;
import java.util.List;
import java.util.regex.Pattern;

public class TestableSpecification implements Specification<String> {

    private final String[] value;
    private final Pattern p;

    public TestableSpecification(final String[] value) {
        assert value != null && value.length != 0;
        this.value = copyOf(value, value.length);
        this.p = Pattern.compile(String.format("[%s]+", stream(this.value).collect(joining())));
    }

    @Override
    public boolean isSatisfiedBy(final String candidate) {
        return p.matcher(candidate).find();
    }

    @Override
    public boolean isGeneralizationOf(final Specification<String> specification) {
        if (this == specification) {
            return true;
        }
        if (specification == null) {
            return false;
        }
        if (specification instanceof CompositeSpecification) {
            final List<Specification<String>> components = ((CompositeSpecification<String>) specification).getComponents();
            if (specification instanceof ConjunctionSpecification) {
                return components.stream().anyMatch(s -> s.isSpecialCaseOf(this));
            }
            if (specification instanceof DisjunctionSpecification) {
                return components.stream().allMatch(s -> s.isSpecialCaseOf(this));
            }
            throw new UnsupportedOperationException();
        } else {
            if (specification instanceof TestableSpecification) {
                return stream(value).allMatch(s1 -> {
                    return stream(((TestableSpecification) specification).value).anyMatch(s2 -> {
                        return s1.equals(s2);
                    });
                });
            } else {
                // TODO
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public String toString() {
        return stream(this.value).collect(joining());
    }
}
