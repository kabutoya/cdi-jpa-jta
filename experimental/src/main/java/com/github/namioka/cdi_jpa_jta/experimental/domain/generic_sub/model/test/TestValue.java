package com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.model.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.ValueObject;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@EqualsAndHashCode @ToString @Getter
public class TestValue implements ValueObject<TestValue> {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String value1;
    @NotNull
    private String value2;

    public TestValue(final String value1, final String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    TestValue() {
    }
}
