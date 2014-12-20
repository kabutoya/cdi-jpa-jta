package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.Specification;
import java.util.regex.Pattern;
import lombok.ToString;

@ToString
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
//        if (!(specification instanceof TestableSpecification)) {
//            throw new IllegalArgumentException("specification must be instance of TestableSpecification");
//        }
        if (this == specification) {
            return true;
        }
        if (specification == null) {
            return false;
        }
        if (this.getClass() == specification.getClass()) {
            return stream(value).allMatch(s1 -> stream(((TestableSpecification) specification).value).anyMatch(s2 -> s1.equals(s2)));
        }
        return false;
    }
}
