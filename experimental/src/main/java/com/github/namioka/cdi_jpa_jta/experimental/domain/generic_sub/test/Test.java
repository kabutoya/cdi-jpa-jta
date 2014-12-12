package com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.ReferenceObject;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode @ToString @Getter
public class Test implements ReferenceObject<Test, Long> {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Embedded
    @Valid
    @Setter
    private TestValue testValue;

    public Test(TestValue testValue) {
        this.testValue = testValue;
    }

    Test() {
    }
}
