package com.github.namioka.cdi_jpa_jta.experimental.domain.generic_sub.test;

import com.github.namioka.cdi_jpa_jta.experimental.domain.conception.Persistable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Test implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotNull
    @Getter @Setter
    private String value1;

    @NotNull
    @Getter @Setter
    private String value2;

    private Test() {
    }

    public Test(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
