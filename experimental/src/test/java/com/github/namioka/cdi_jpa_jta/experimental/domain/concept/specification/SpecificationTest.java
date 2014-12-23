package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification.stub.TestableSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@Slf4j
public class SpecificationTest {

    @Rule
    public TestName name = new TestName();
    private static final Specification<String> CONTAINS_ALPHABET = new TestableSpecification(new String[]{"A"});
    private static final Specification<String> CONTAINS_NUMERIC = new TestableSpecification(new String[]{"0"});
    private static final Specification<String> CONTAINS_SYMBOL = new TestableSpecification(new String[]{"#"});
    private static final Specification<String> CONTAINS_ALPHABET_AND_NUMERIC = CONTAINS_ALPHABET.and(CONTAINS_NUMERIC);
    private static final Specification<String> CONTAINS_ALPHABET_OR_NUMERIC = CONTAINS_ALPHABET.or(CONTAINS_NUMERIC);
    private static final Specification<String> CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL = CONTAINS_ALPHABET.and(CONTAINS_NUMERIC).and(CONTAINS_SYMBOL);
    private static final Specification<String> CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL = CONTAINS_ALPHABET.and(CONTAINS_NUMERIC).or(CONTAINS_SYMBOL);
    private static final Specification<String> CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL = CONTAINS_ALPHABET.or(CONTAINS_NUMERIC).and(CONTAINS_SYMBOL);
    private static final Specification<String> CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL = CONTAINS_ALPHABET.or(CONTAINS_NUMERIC).or(CONTAINS_SYMBOL);
    private static final Specification<String> ABC_ = new TestableSpecification(new String[]{"A", "B", "C"});
    private static final Specification<String> AB__ = new TestableSpecification(new String[]{"A", "B"});
    private static final Specification<String> A_C_ = new TestableSpecification(new String[]{"A", "C"});
    private static final Specification<String> _BC_ = new TestableSpecification(new String[]{"B", "C"});
    private static final Specification<String> A___ = new TestableSpecification(new String[]{"A"});
    private static final Specification<String> _B__ = new TestableSpecification(new String[]{"B"});
    private static final Specification<String> __C_ = new TestableSpecification(new String[]{"C"});
    private static final Specification<String> ___X = new TestableSpecification(new String[]{"X"});

    @Before
    public void before() {
        if (log.isDebugEnabled()) {
            log.debug(String.format("[START] %s", name.getMethodName()));
        }
    }

    @After
    public void after() {
        if (log.isDebugEnabled()) {
            log.debug("[END]\n");
        }
    }

    @Test
    public void test_isSatisfiedBy() {
        // alphabet
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("0#"), is(false));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("A#"), is(true));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("A"), is(true));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("0"), is(false));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy("#"), is(false));
        assertThat("", CONTAINS_ALPHABET.isSatisfiedBy(""), is(false));
        // numeric
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("0#"), is(true));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("A#"), is(false));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("A"), is(false));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("0"), is(true));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy("#"), is(false));
        assertThat("", CONTAINS_NUMERIC.isSatisfiedBy(""), is(false));
        // symbol
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("A0"), is(false));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("0#"), is(true));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("A#"), is(true));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("A"), is(false));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("0"), is(false));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy("#"), is(true));
        assertThat("", CONTAINS_SYMBOL.isSatisfiedBy(""), is(false));
        // alphabet and numeric
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("0#"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("A#"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("A"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("0"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy("#"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSatisfiedBy(""), is(false));
        // alphabet or numeric
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("A#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("A"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("0"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy("#"), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSatisfiedBy(""), is(false));
        // alphabet and numeric and symbol
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("A0"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("0#"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("A#"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("A"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("0"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy("#"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSatisfiedBy(""), is(false));
        // (alphabet and numeric) or symbol
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("A#"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("A"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("0"), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy("#"), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSatisfiedBy(""), is(false));
        // alphabet or (numeric and symbol)
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("A#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("A"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("0"), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy("#"), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSatisfiedBy(""), is(false));
        // alphabet or numeric or symbol
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("A0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("A0"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("0#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("A#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("A"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("0"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy("#"), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSatisfiedBy(""), is(false));
    }

    @Test
    public void test_isSpecialCaseOf() {
        //
        assertThat("", ABC_.isSpecialCaseOf(ABC_), is(true));
        assertThat("", ABC_.isSpecialCaseOf(AB__), is(true));
        assertThat("", ABC_.isSpecialCaseOf(A_C_), is(true));
        assertThat("", ABC_.isSpecialCaseOf(_BC_), is(true));
        assertThat("", ABC_.isSpecialCaseOf(A___), is(true));
        assertThat("", ABC_.isSpecialCaseOf(_B__), is(true));
        assertThat("", ABC_.isSpecialCaseOf(__C_), is(true));
        assertThat("", ABC_.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", AB__.isSpecialCaseOf(ABC_), is(false));
        assertThat("", AB__.isSpecialCaseOf(AB__), is(true));
        assertThat("", AB__.isSpecialCaseOf(A_C_), is(false));
        assertThat("", AB__.isSpecialCaseOf(_BC_), is(false));
        assertThat("", AB__.isSpecialCaseOf(A___), is(true));
        assertThat("", AB__.isSpecialCaseOf(_B__), is(true));
        assertThat("", AB__.isSpecialCaseOf(__C_), is(false));
        assertThat("", AB__.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", A_C_.isSpecialCaseOf(ABC_), is(false));
        assertThat("", A_C_.isSpecialCaseOf(AB__), is(false));
        assertThat("", A_C_.isSpecialCaseOf(A_C_), is(true));
        assertThat("", A_C_.isSpecialCaseOf(_BC_), is(false));
        assertThat("", A_C_.isSpecialCaseOf(A___), is(true));
        assertThat("", A_C_.isSpecialCaseOf(_B__), is(false));
        assertThat("", A_C_.isSpecialCaseOf(__C_), is(true));
        assertThat("", A_C_.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", _BC_.isSpecialCaseOf(ABC_), is(false));
        assertThat("", _BC_.isSpecialCaseOf(AB__), is(false));
        assertThat("", _BC_.isSpecialCaseOf(A_C_), is(false));
        assertThat("", _BC_.isSpecialCaseOf(_BC_), is(true));
        assertThat("", _BC_.isSpecialCaseOf(A___), is(false));
        assertThat("", _BC_.isSpecialCaseOf(_B__), is(true));
        assertThat("", _BC_.isSpecialCaseOf(__C_), is(true));
        assertThat("", _BC_.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", A___.isSpecialCaseOf(ABC_), is(false));
        assertThat("", A___.isSpecialCaseOf(AB__), is(false));
        assertThat("", A___.isSpecialCaseOf(A_C_), is(false));
        assertThat("", A___.isSpecialCaseOf(_BC_), is(false));
        assertThat("", A___.isSpecialCaseOf(A___), is(true));
        assertThat("", A___.isSpecialCaseOf(_B__), is(false));
        assertThat("", A___.isSpecialCaseOf(__C_), is(false));
        assertThat("", A___.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", _B__.isSpecialCaseOf(ABC_), is(false));
        assertThat("", _B__.isSpecialCaseOf(AB__), is(false));
        assertThat("", _B__.isSpecialCaseOf(A_C_), is(false));
        assertThat("", _B__.isSpecialCaseOf(_BC_), is(false));
        assertThat("", _B__.isSpecialCaseOf(A___), is(false));
        assertThat("", _B__.isSpecialCaseOf(_B__), is(true));
        assertThat("", _B__.isSpecialCaseOf(__C_), is(false));
        assertThat("", _B__.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", __C_.isSpecialCaseOf(ABC_), is(false));
        assertThat("", __C_.isSpecialCaseOf(AB__), is(false));
        assertThat("", __C_.isSpecialCaseOf(A_C_), is(false));
        assertThat("", __C_.isSpecialCaseOf(_BC_), is(false));
        assertThat("", __C_.isSpecialCaseOf(A___), is(false));
        assertThat("", __C_.isSpecialCaseOf(_B__), is(false));
        assertThat("", __C_.isSpecialCaseOf(__C_), is(true));
        assertThat("", __C_.isSpecialCaseOf(___X), is(false));
        //
        assertThat("", ___X.isSpecialCaseOf(ABC_), is(false));
        assertThat("", ___X.isSpecialCaseOf(AB__), is(false));
        assertThat("", ___X.isSpecialCaseOf(A_C_), is(false));
        assertThat("", ___X.isSpecialCaseOf(_BC_), is(false));
        assertThat("", ___X.isSpecialCaseOf(A___), is(false));
        assertThat("", ___X.isSpecialCaseOf(_B__), is(false));
        assertThat("", ___X.isSpecialCaseOf(__C_), is(false));
        assertThat("", ___X.isSpecialCaseOf(___X), is(true));
        //
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET), is(true));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(true));
        //
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET), is(false));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_NUMERIC), is(true));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC), is(false));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC), is(true));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(false));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(true));
        //
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET), is(false));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_NUMERIC), is(false));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_SYMBOL), is(true));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC), is(false));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC), is(false));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(true));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(true));
        //
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSpecialCaseOf(CONTAINS_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSpecialCaseOf(CONTAINS_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC), is(true));
        //
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(true));
        //
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(false));
        //
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(true));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(false));
        //
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_NUMERIC), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_AND_NUMERIC_OR_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_AND_SYMBOL), is(false));
        assertThat("", CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL.isSpecialCaseOf(CONTAINS_ALPHABET_OR_NUMERIC_OR_SYMBOL), is(true));
    }

    @Test
    public void test_isGeneralizationOf() {
    }
    private static final Specification<String> A = new TestableSpecification(new String[]{"A"});
    private static final Specification<String> B = new TestableSpecification(new String[]{"B"});
    private static final Specification<String> C = new TestableSpecification(new String[]{"C"});
    private static final Specification<String> D = new TestableSpecification(new String[]{"D"});
    private static final Specification<String> E = new TestableSpecification(new String[]{"E"});

    @Test
    public void test_composition_4and_0or() {
        assertThat("", A.and(B).and(C).and(D).and(E).toString(), is(equalTo("(A && B && C && D && E)")));
    }

    @Test
    public void test_composition_3and_1or() {
        assertThat("", A.or(B).and(C).and(D).and(E).toString(), is(equalTo("(A || (B && C && D && E))")));
        assertThat("", A.and(B).or(C).and(D).and(E).toString(), is(equalTo("((A && B) || (C && D && E))")));
        assertThat("", A.and(B).and(C).or(D).and(E).toString(), is(equalTo("((A && B && C) || (D && E))")));
        assertThat("", A.and(B).and(C).and(D).or(E).toString(), is(equalTo("((A && B && C && D) || E)")));
    }

    @Test
    public void test_composition_2and_2or() {
        assertThat("", A.or(B).or(C).and(D).and(E).toString(), is(equalTo("(A || B || (C && D && E))")));
        assertThat("", A.or(B).and(C).or(D).and(E).toString(), is(equalTo("(A || (B && C) || (D && E))")));
        assertThat("", A.or(B).and(C).and(D).or(E).toString(), is(equalTo("(A || (B && C && D) || E)")));
        assertThat("", A.and(B).or(C).or(D).and(E).toString(), is(equalTo("((A && B) || C || (D && E))")));
        assertThat("", A.and(B).or(C).and(D).or(E).toString(), is(equalTo("((A && B) || (C && D) || E)")));
        assertThat("", A.and(B).and(C).or(D).or(E).toString(), is(equalTo("((A && B && C) || D || E)")));
    }

    @Test
    public void test_composition_1and_3or() {
        assertThat("", A.or(B).or(C).or(D).and(E).toString(), is(equalTo("(A || B || C || (D && E))")));
        assertThat("", A.or(B).or(C).and(D).or(E).toString(), is(equalTo("(A || B || (C && D) || E)")));
        assertThat("", A.and(B).or(C).or(D).or(E).toString(), is(equalTo("((A && B) || C || D || E)")));
    }

    @Test
    public void test_composition_0and_4or() {
        assertThat("", A.or(B).or(C).or(D).or(E).toString(), is(equalTo("(A || B || C || D || E)")));
    }
}
