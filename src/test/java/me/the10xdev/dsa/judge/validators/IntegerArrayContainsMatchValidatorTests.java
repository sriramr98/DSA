package me.the10xdev.dsa.judge.validators;

import me.the10xdev.dsa.judge.parser_output.compound.IntegerArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerArrayContainsMatchValidatorTests {

    private static ContainsArrayValidator<IntegerArray, Integer> validator;

    @BeforeAll
    public static void init() {
        validator = new ContainsArrayValidator<>();
    }

    @Test
    public void returnTrueForValidComparison() {

        IntegerArray expected = new IntegerArray(List.of(1, 2, 3));
        IntegerArray actual = new IntegerArray(List.of(1, 2, 3));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnTrueForMixedOrderArrays() {
        IntegerArray expected = new IntegerArray(List.of(1, 2, 3));
        IntegerArray actual = new IntegerArray(List.of(3, 2, 1));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnTrueForMixedOrderDuplicateElementArrays() {
        IntegerArray expected = new IntegerArray(List.of(1, 1, 1, 2, 2, 4, 5));
        IntegerArray actual = new IntegerArray(List.of(2, 1, 4, 5, 2, 1, 1));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnTrueForEmptyArrays() {
        IntegerArray expected = new IntegerArray(List.of());
        IntegerArray actual = new IntegerArray(List.of());

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertTrue(isValid);
    }

    @Test
    public void returnFalseForInvalidComparison() {
        IntegerArray expected = new IntegerArray(List.of(1, 2, 3));
        IntegerArray actual = new IntegerArray(List.of(3));

        boolean isValid = validator.validateExpectedWithActual(expected, actual);

        assertFalse(isValid);
    }

}
