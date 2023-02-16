package me.the10xdev.dsa.judge.validators;

import me.the10xdev.dsa.judge.parser_output.compound.StringArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringArrayExactMatchValidatorTests {

    private static ExactArrayValidator<StringArray, String> validator;

    @BeforeAll
    public static void init() {
        validator = new ExactArrayValidator<>();
    }

    @Test
    public void returnTrueForValidComparison() {

        StringArray expected = new StringArray(List.of("abc", "def", "ghi"));
        StringArray actual = new StringArray(List.of("abc", "def", "ghi"));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnFalseForMixedOrderArrays() {
        StringArray expected = new StringArray(List.of("abc", "def", "ghi"));
        StringArray actual = new StringArray(List.of("def", "abc", "ghi"));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertFalse(isValid);
    }

    @Test
    public void returnFalseForMixedOrderDuplicateElementArrays() {
        StringArray expected = new StringArray(List.of("abc", "abc", "abc", "abc", "def", "def", "def", "ghi"));
        StringArray actual = new StringArray(List.of("abc", "abc", "def","abc", "abc", "def", "ghi", "def"));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertFalse(isValid);
    }

    @Test
    public void returnTrueForInOrderDuplicateElementArrays() {
        StringArray expected = new StringArray(List.of("abc", "abc", "abc", "abc", "def", "def", "def", "ghi"));
        StringArray actual = new StringArray(List.of("abc", "abc", "abc", "abc", "def", "def", "def", "ghi"));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnTrueForEmptyArrays() {
        StringArray expected = new StringArray(List.of());
        StringArray actual = new StringArray(List.of());

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnFalseForInvalidComparison() {
        StringArray expected = new StringArray(List.of("abc", "def"));
        StringArray actual = new StringArray(List.of("abc", "h"));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertFalse(isValid);
    }

}
