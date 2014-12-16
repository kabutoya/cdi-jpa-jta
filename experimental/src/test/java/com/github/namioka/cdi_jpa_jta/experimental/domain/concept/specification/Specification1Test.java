package com.github.namioka.cdi_jpa_jta.experimental.domain.concept.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Specification1Test {

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

        Specification<String> alphabetAndNumericAndSymbolSpecification = alphabetSpecification.and(numericSpecification).and(symbolSpecification);
        System.out.println("A----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A0@"), is(true));
        System.out.println("B----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A0"), is(false));
        System.out.println("C----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A@"), is(false));
        System.out.println("D----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("0@"), is(false));
        System.out.println("E----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("A"), is(false));
        System.out.println("F----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("0"), is(false));
        System.out.println("G----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy("@"), is(false));
        System.out.println("H----------");
        assertThat("", alphabetAndNumericAndSymbolSpecification.isSatisfiedBy(""), is(false));

        Specification<String> alphabetOrNumericOrSymbolSpecification = alphabetSpecification.or(numericSpecification).or(symbolSpecification);
        System.out.println("I----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A0@"), is(true));
        System.out.println("J----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A0"), is(true));
        System.out.println("K----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A@"), is(true));
        System.out.println("L----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("0@"), is(true));
        System.out.println("M----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("A"), is(true));
        System.out.println("N----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("0"), is(true));
        System.out.println("O----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy("@"), is(true));
        System.out.println("P----------");
        assertThat("", alphabetOrNumericOrSymbolSpecification.isSatisfiedBy(""), is(false));
    }

    private abstract static class StringTypeSpecification implements LeafSpecification<String> {

        protected Pattern p;

        @Override
        public boolean isSatisfiedBy(String candidateObject) {
            return p.matcher(candidateObject).find();
        }
    }

    private static class AlphabetSpecification extends StringTypeSpecification {

        public AlphabetSpecification() {
            //p = Pattern.compile("^[A-Za-z]+$");
            p = Pattern.compile("[A-Za-z]");
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private static class NumericSpecification extends StringTypeSpecification {

        public NumericSpecification() {
            //p = Pattern.compile("^[0-9]+$");
            p = Pattern.compile("[0-9]");
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private static class SymbolSpecification extends StringTypeSpecification {

        public SymbolSpecification() {
            //p = Pattern.compile("^[@]+$");
            p = Pattern.compile("[@]");
        }

        @Override
        public boolean isGeneralizationOf(Specification<String> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
