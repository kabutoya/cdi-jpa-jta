package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub;

import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.*;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.Specification;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TestableSpecification implements Specification<String> {

    private final String[] value;
    private final Pattern p;

    public TestableSpecification(final String[] value) {
        assert value != null && value.length != 0;
        this.value = Arrays.copyOf(value, value.length);
        this.p = Pattern.compile(String.format("^[%s]+$", of(value).collect(joining())));
    }

    @Override
    public boolean isSatisfiedBy(final String candidate) {
        return p.matcher(candidate).find();
    }

    @Override
    public boolean isGeneralizationOf(final Specification<String> specification) {
        if (!(specification instanceof TestableSpecification)) {
            throw new IllegalArgumentException("specification must be instance of TestableSpecification");
        }
        return of(value).allMatch(s1 -> of(((TestableSpecification) specification).value).anyMatch(s2 -> s1.equals(s2)));
    }
}
