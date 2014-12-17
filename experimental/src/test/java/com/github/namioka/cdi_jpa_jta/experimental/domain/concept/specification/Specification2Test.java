package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.stream.Stream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Specification2Test {

    private static final AlphabetCharacterSpecification ABC__SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"A", "B", "C"});
    private static final AlphabetCharacterSpecification AB___SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"A", "B"});
    private static final AlphabetCharacterSpecification A_C__SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"A", "C"});
    private static final AlphabetCharacterSpecification _BC__SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"B", "C"});
    private static final AlphabetCharacterSpecification A____SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"A"});
    private static final AlphabetCharacterSpecification _B___SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"B"});
    private static final AlphabetCharacterSpecification __C__SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"C"});
    private static final AlphabetCharacterSpecification ___X_SPECIFICATION = new AlphabetCharacterSpecification(new String[]{"X"});

    @Test
    public void test_isSpecialCaseOf() {
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(true));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(true));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(true));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(true));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(true));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(true));
        assertThat("", A_C__SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(true));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(true));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(true));
        assertThat("", _BC__SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(true));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(true));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(true));
        assertThat("", __C__SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(ABC__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(AB___SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(A_C__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(_BC__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(A____SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(_B___SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(__C__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isSpecialCaseOf(___X_SPECIFICATION), is(true));
    }

    @Test
    public void test_isGeneralizationOf() {
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(false));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(false));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(false));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", ABC__SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(true));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", AB___SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(true));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", A_C__SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(true));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", _BC__SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(true));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(true));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(true));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", A____SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(true));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(true));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(true));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", _B___SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(true));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(true));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(true));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(true));
        assertThat("", __C__SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(ABC__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(AB___SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(A_C__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(_BC__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(A____SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(_B___SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(__C__SPECIFICATION), is(false));
        assertThat("", ___X_SPECIFICATION.isGeneralizationOf(___X_SPECIFICATION), is(true));
    }

    private static class AlphabetCharacterSpecification implements ValueBoundSpecification<String, String[]> {

        @Getter
        private final String[] value;

        //private final Pattern p;
        public AlphabetCharacterSpecification(String[] value) {
            this.value = value;
            //this.p = Pattern.compile(String.format("^[%s]+$", Stream.of(value).collect(joining())));
        }

        @Override
        public boolean isSatisfiedBy(String candidateObject) {
            throw new UnsupportedOperationException("Not supported yet.");
            //return p.matcher(candidateObject).find();
        }
//        @Override
//        public boolean isSpecialCaseOf(Specification<String> specification) {
//            if (!(specification instanceof ValueBoundSpecification)) {
//                throw new IllegalArgumentException("specification must be instance of ValueBoundSpecification");
//            }
//            //return Stream.of((String[]) ((ValueBoundSpecification) specification).getValue()).allMatch(ov -> Stream.of(value).anyMatch(tv -> ov.equals(tv)));
//            //return Stream.of((String[]) ((ValueBoundSpecification) specification).getValue()).allMatch(ov -> {
//            //    return Stream.of(value).anyMatch(tv -> {
//            //        return ov.equals(tv);
//            //    });
//            //});
//            return specification.isGeneralizationOf(this);
//        }
//

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            if (!(specification instanceof ValueBoundSpecification)) {
                throw new IllegalArgumentException("specification must be instance of ValueBoundSpecification");
            }
            //return Stream.of(value).allMatch(tv -> Stream.of((String[]) ((ValueBoundSpecification) specification).getValue()).anyMatch(ov -> tv.equals(ov)));
            return Stream.of(value).allMatch(tv -> {
                return Stream.of((String[]) ((ValueBoundSpecification) specification).getValue()).anyMatch(ov -> {
                    return tv.equals(ov);
                });
            });
        }
    }
}
