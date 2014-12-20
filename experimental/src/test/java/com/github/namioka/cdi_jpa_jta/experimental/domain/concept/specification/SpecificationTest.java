package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub.TestableSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SpecificationTest {

    private static final TestableSpecification ALPHABET_SPECIFICATION = new TestableSpecification(new String[]{"A"});
    private static final TestableSpecification NUMERIC_SPECIFICATION = new TestableSpecification(new String[]{"0"});
    private static final TestableSpecification SYMBOL_SPECIFICATION = new TestableSpecification(new String[]{"#"});
    private static final Specification<String> ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION = ALPHABET_SPECIFICATION.and(NUMERIC_SPECIFICATION).and(SYMBOL_SPECIFICATION);
    private static final Specification<String> ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION = ALPHABET_SPECIFICATION.and(NUMERIC_SPECIFICATION).or(SYMBOL_SPECIFICATION);
    private static final Specification<String> ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION = ALPHABET_SPECIFICATION.or(NUMERIC_SPECIFICATION).and(SYMBOL_SPECIFICATION);
    private static final Specification<String> ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION = ALPHABET_SPECIFICATION.or(NUMERIC_SPECIFICATION).or(SYMBOL_SPECIFICATION);
    private static final TestableSpecification ABC__SPECIFICATION = new TestableSpecification(new String[]{"A", "B", "C"});
    private static final TestableSpecification AB___SPECIFICATION = new TestableSpecification(new String[]{"A", "B"});
    private static final TestableSpecification A_C__SPECIFICATION = new TestableSpecification(new String[]{"A", "C"});
    private static final TestableSpecification _BC__SPECIFICATION = new TestableSpecification(new String[]{"B", "C"});
    private static final TestableSpecification A____SPECIFICATION = new TestableSpecification(new String[]{"A"});
    private static final TestableSpecification _B___SPECIFICATION = new TestableSpecification(new String[]{"B"});
    private static final TestableSpecification __C__SPECIFICATION = new TestableSpecification(new String[]{"C"});
    private static final TestableSpecification ___X_SPECIFICATION = new TestableSpecification(new String[]{"X"});

    @Test
    public void test_isSatisfiedBy() {
        // alphabet
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("A0"), is(true));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("0#"), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("A#"), is(true));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("A"), is(true));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("0"), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy("#"), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSatisfiedBy(""), is(false));
        // numeric
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("A0"), is(true));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("0#"), is(true));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("A#"), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("A"), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("0"), is(true));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy("#"), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSatisfiedBy(""), is(false));
        // symbol
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("A0"), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("0#"), is(true));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("A#"), is(true));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("A"), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("0"), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy("#"), is(true));
        assertThat("", SYMBOL_SPECIFICATION.isSatisfiedBy(""), is(false));
        // alphabet and numeric and symbol
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A0"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("0#"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A#"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("0"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("#"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy(""), is(false));
        // (alphabet and numeric) or symbol
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A0"), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("0#"), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A#"), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("0"), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("#"), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy(""), is(false));
        // (alphabet or numeric) and symbol
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A0"), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("0#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("A"), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("0"), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy("#"), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSatisfiedBy(""), is(false));
        // alphabet or numeric or symbol
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A0#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A0"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("0#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("A"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("0"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy("#"), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSatisfiedBy(""), is(false));
    }

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
        //
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(true));
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        //
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(true));
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", NUMERIC_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        //
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(true));
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        //
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false)); // TODO
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(false));
        //
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        //
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        //
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_SPECIFICATION), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(NUMERIC_SPECIFICATION), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(SYMBOL_SPECIFICATION), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_AND_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_AND_SYMBOL_SPECIFICATION), is(false));
        assertThat("", ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION.isSpecialCaseOf(ALPHABET_OR_NUMERIC_OR_SYMBOL_SPECIFICATION), is(true));
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
}
