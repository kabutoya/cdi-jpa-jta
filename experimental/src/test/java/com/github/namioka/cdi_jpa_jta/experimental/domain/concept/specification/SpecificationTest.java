package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SpecificationTest {

//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(); // create once, reuse
//
    @Test
    public void test_isSatisfiedBy() throws JsonProcessingException {
        AlphabetSpecification alphabetSpecification = new AlphabetSpecification();
        assertThat("", alphabetSpecification.isSatisfiedBy("A0@"), is(true));
        assertThat("", alphabetSpecification.isSatisfiedBy("A0"), is(true));
        assertThat("", alphabetSpecification.isSatisfiedBy("A@"), is(true));
        assertThat("", alphabetSpecification.isSatisfiedBy("0@"), is(false));
        assertThat("", alphabetSpecification.isSatisfiedBy("A"), is(true));
        assertThat("", alphabetSpecification.isSatisfiedBy("0"), is(false));
        assertThat("", alphabetSpecification.isSatisfiedBy("@"), is(false));
        assertThat("", alphabetSpecification.isSatisfiedBy(""), is(false));

        NumericSpecification numericSpecification = new NumericSpecification();
        assertThat("", numericSpecification.isSatisfiedBy("A0@"), is(true));
        assertThat("", numericSpecification.isSatisfiedBy("A0"), is(true));
        assertThat("", numericSpecification.isSatisfiedBy("A@"), is(false));
        assertThat("", numericSpecification.isSatisfiedBy("0@"), is(true));
        assertThat("", numericSpecification.isSatisfiedBy("A"), is(false));
        assertThat("", numericSpecification.isSatisfiedBy("0"), is(true));
        assertThat("", numericSpecification.isSatisfiedBy("@"), is(false));
        assertThat("", numericSpecification.isSatisfiedBy(""), is(false));

        SymbolSpecification symbolSpecification = new SymbolSpecification();
        assertThat("", symbolSpecification.isSatisfiedBy("A0@"), is(true));
        assertThat("", symbolSpecification.isSatisfiedBy("A0"), is(false));
        assertThat("", symbolSpecification.isSatisfiedBy("A@"), is(true));
        assertThat("", symbolSpecification.isSatisfiedBy("0@"), is(true));
        assertThat("", symbolSpecification.isSatisfiedBy("A"), is(false));
        assertThat("", symbolSpecification.isSatisfiedBy("0"), is(false));
        assertThat("", symbolSpecification.isSatisfiedBy("@"), is(true));
        assertThat("", symbolSpecification.isSatisfiedBy(""), is(false));

        //Specification<String> alphabetAndNumericAndSymbolSpecification = alphabetSpecification.and(numericSpecification.and(symbolSpecification));
        //Specification<String> alphabetAndNumericAndSymbolSpecification = alphabetSpecification.and(numericSpecification).and(symbolSpecification);
        Specification<String> alphabetAndNumericAndSymbolSpecification = alphabetSpecification.and(numericSpecification, symbolSpecification);
        //Specification<String> alphabetAndNumericAndSymbolSpecification = Specifications.and(alphabetSpecification, numericSpecification, symbolSpecification);
        if (log.isDebugEnabled()) {
            log.debug(alphabetAndNumericAndSymbolSpecification.toString());
        }
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A0@"), is(true));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A0"), is(false));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A@"), is(false));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("0@"), is(false));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A"), is(false));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("0"), is(false));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("@"), is(false));
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy(""), is(false));

        //Specification<String> alphabetOrNumericOrSymbolSpecification = alphabetSpecification.or(numericSpecification.or(symbolSpecification));
        //Specification<String> alphabetOrNumericOrSymbolSpecification = alphabetSpecification.or(numericSpecification).or(symbolSpecification);
        Specification<String> alphabetOrNumericOrSymbolSpecification = alphabetSpecification.or(numericSpecification, symbolSpecification);
        //Specification<String> alphabetOrNumericOrSymbolSpecification = Specifications.or(alphabetSpecification, numericSpecification, symbolSpecification);
        if (log.isDebugEnabled()) {
            log.debug(alphabetOrNumericOrSymbolSpecification.toString());
        }
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A0@"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A0"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A@"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("0@"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("0"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("@"), is(true));
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy(""), is(false));
    }

    //@ToString
    private abstract static class StringTypeSpecification<String> implements LeafSpecification<String> {

        protected Pattern p;

        @Override
        public boolean isSatisfiedBy(String candidateObject) {
            return p.matcher((CharSequence) candidateObject).find();
        }
    }

    //@ToString
    private static class AlphabetSpecification<String> extends StringTypeSpecification<String> {

        public AlphabetSpecification() {
            //p = Pattern.compile("^[A-Za-z]+$");
            p = Pattern.compile("[A-Za-z]");
        }

        @Override
        public boolean isSpecialCaseOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    //@ToString
    private static class NumericSpecification<String> extends StringTypeSpecification<String> {

        public NumericSpecification() {
            //p = Pattern.compile("^[0-9]+$");
            p = Pattern.compile("[0-9]");
        }

        @Override
        public boolean isSpecialCaseOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    //@ToString
    private static class SymbolSpecification<String> extends StringTypeSpecification<String> {

        public SymbolSpecification() {
            //p = Pattern.compile("^[@]+$");
            p = Pattern.compile("[@]");
        }

        @Override
        public boolean isSpecialCaseOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
