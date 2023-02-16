package me.the10xdev.dsa.judge.validators;

import me.the10xdev.dsa.judge.parser_output.compound.ArrayOutput;

import java.util.List;

public class ExactArrayValidator<ArrayType extends ArrayOutput<ElementType>, ElementType> implements ResultValidator<ArrayType> {
    @Override
    public boolean validateExpectedWithActual(ArrayType expected, ArrayType actual) {

        List<ElementType> expectedValues = expected.getValues();
        List<ElementType> actualValues = actual.getValues();

        int expectedValuesSize = expectedValues.size();
        int actualValuesSize = actualValues.size();

        if (expectedValuesSize != actualValuesSize) return false;

        for (int i=0; i<expectedValuesSize; i++) {
            ElementType expectedValue = expectedValues.get(i);
            ElementType actualValue = actualValues.get(i);

            if (!expectedValue.equals(actualValue)) {
                return false;
            }
        }

        return true;
    }

}
